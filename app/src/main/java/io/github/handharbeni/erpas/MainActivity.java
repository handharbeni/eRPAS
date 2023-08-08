package io.github.handharbeni.erpas;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dantsu.escposprinter.EscPosPrinter;
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections;
import com.dantsu.escposprinter.exceptions.EscPosBarcodeException;
import com.dantsu.escposprinter.exceptions.EscPosConnectionException;
import com.dantsu.escposprinter.exceptions.EscPosEncodingException;
import com.dantsu.escposprinter.exceptions.EscPosParserException;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothClassicService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothConfiguration;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothStatus;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import io.github.handharbeni.erpas.apis.responses.WP.PaymentStatus;
import io.github.handharbeni.erpas.apis.responses.WP.PrintWp;
import io.github.handharbeni.erpas.cores.BaseActivity;
import io.github.handharbeni.erpas.database.AppDb;
import io.github.handharbeni.erpas.databinding.ActivityMainBinding;
import io.github.handharbeni.erpas.events.BluetoothEvent;
import io.github.handharbeni.erpas.events.MessageEvent;
import io.github.handharbeni.erpas.ui.bt.BluetoothFragment;
import io.github.handharbeni.erpas.ui.wp.WpSearchFragment;
import io.github.handharbeni.erpas.utils.Constant;
import io.github.handharbeni.erpas.utils.Util;
import io.github.handharbeni.erpas.utils.UtilNav;
import io.github.handharbeni.erpas.utils.UtilPermission;

public class MainActivity extends BaseActivity implements BluetoothService.OnBluetoothEventCallback, BluetoothService.OnBluetoothScanCallback{

	public static boolean isInBackStack = false;

	private ActivityMainBinding binding;
	boolean doubleBackToExitPressedOnce = false;
	NavController navController;

	public static boolean bluetoothConnected = true;
	private BluetoothAdapter bluetoothAdapter;
	private BluetoothConfiguration config;
	private BluetoothService service;
	private AppDb appDb;

	List<BluetoothDevice> listDevice = new ArrayList<>();
	private final ActivityResultLauncher<String[]> requestPermissionLauncher =
			registerForActivityResult(
					new ActivityResultContracts.RequestMultiplePermissions(),
					this::responsePermissions
			);
	ActivityResultLauncher<Intent> mainActivityLauncher = registerForActivityResult(
			new ActivityResultContracts.StartActivityForResult(),
			this::resultActivity);


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		requestPermission();

		AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
				R.id.navigation_home, R.id.navigation_print)
				.build();
		navController =
				Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
		navController.addOnDestinationChangedListener(this::observeChild);
		NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

		binding.navView.setOnMenuItemClickListener(item -> {
			navController.navigate(item.getItemId());
			return true;
		});

		binding.fabScanQr.setOnClickListener(view -> {
			Bundle bundle = new Bundle();
			bundle.putBoolean(WpSearchFragment.DIRECT_SCAN, true);
			navController.navigate(R.id.navigation_search_wp, bundle);
		});
	}

	@Override
	public void onBackPressed() {
		if (isInBackStack) {
			isInBackStack = false;
			super.onBackPressed();
		} else {
			if (doubleBackToExitPressedOnce) {
				this.finish();
				return;
			}

			this.doubleBackToExitPressedOnce = true;
			Toast.makeText(
					     this,
					     "Please click BACK again to exit",
					     Toast.LENGTH_SHORT
			     )
			     .show();

			new Handler(Looper.getMainLooper())
					.postDelayed(() -> doubleBackToExitPressedOnce = false
							, 2000);
		}
	}

	@Override
	public boolean onSupportNavigateUp() {
		return isInBackStack && (navController.navigateUp() || super.onSupportNavigateUp());
	}

	private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(
			new ScanContract(),
			result -> {
				if (result.getContents() == null) {
					Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
				} else {
					Toast
							.makeText(MainActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG)
							.show();
				}
			}
	);

	@Override
	public void onDataRead(byte[] buffer, int length) {

	}

	@Override
	public void onStatusChange(BluetoothStatus status) {
		if (status == BluetoothStatus.CONNECTED) {
			bluetoothConnected = true;
			EventBus.getDefault().post(new BluetoothEvent(BluetoothEvent.BTEvent.BLUETOOTH_CONNECTED, BluetoothStatus.CONNECTED));
//			navController.navigate(R.id.navigation_home);
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					navController.navigate(R.id.navigation_home);
				}
			});
		} else if (status == BluetoothStatus.CONNECTING) {
			bluetoothConnected = false;
			EventBus.getDefault().post(new BluetoothEvent(BluetoothEvent.BTEvent.BLUETOOTH_CONNECTED, BluetoothStatus.CONNECTING));
		} else if (status == BluetoothStatus.NONE) {
			bluetoothConnected = false;
			EventBus.getDefault().post(new BluetoothEvent(BluetoothEvent.BTEvent.BLUETOOTH_CONNECTED, BluetoothStatus.NONE));
		}

		new Thread(() -> {
			new UtilNav<Boolean>().setStateHandle(navController, Constant.BLUETOOTH_CONNECTED, bluetoothConnected);
			new UtilNav<BluetoothStatus>().setStateHandle(navController, Constant.BLUETOOTH_CONNECTED_STRING, status);
		});
	}

	@Override
	public void onDeviceName(String deviceName) {

	}

	@Override
	public void onToast(String message) {

	}

	@Override
	public void onDataWrite(byte[] buffer) {

	}

	@Override
	public void onDeviceDiscovered(BluetoothDevice device, int rssi) {
		if (!UtilPermission.checkPermission(getApplicationContext())) {
			requestPermission();
		}

		if (device.getName() != null) {
			if (!listDevice.contains(device)) {
				listDevice.add(device);
			}
		}

		NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
		AtomicReference<BluetoothFragment> bluetoothFragment = new AtomicReference<>();

		try {
			if (navHostFragment != null) {
				bluetoothFragment.set((BluetoothFragment) navHostFragment.getChildFragmentManager().getFragments().get(0));
			}
			if (bluetoothFragment.get() != null) {
				bluetoothFragment.get().updateDevice(listDevice);
			}
		} catch (Exception ignored) {
		}
	}

	@Override
	public void onStartScan() {

	}

	@Override
	public void onStopScan() {

	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onMessageEvent(MessageEvent event) {

	}

	@Override
	protected void onStart() {
		super.onStart();
		checkBluetoothDevice();
		EventBus.getDefault().register(this);
	}

	@Override
	protected void onStop() {
		EventBus.getDefault().unregister(this);
		super.onStop();
	}

	void requestPermission() {
		Util.enableGps(this);
		String[] listPermission = new String[Constant.LIST_PERMISSION.size()];
		for (int i = 0; i < Constant.LIST_PERMISSION.size(); i++) {
			listPermission[i] = Constant.LIST_PERMISSION.get(i);
		}
		requestPermissionLauncher.launch(listPermission);
	}

	void responsePermissions(Map<String, Boolean> result) {
		boolean readStoragePermission = Boolean.TRUE.equals(result.get(android.Manifest.permission.READ_EXTERNAL_STORAGE));
		boolean writeStoragePermission = Boolean.TRUE.equals(result.get(android.Manifest.permission.WRITE_EXTERNAL_STORAGE));
		boolean fineLocationPermission = Boolean.TRUE.equals(result.get(android.Manifest.permission.ACCESS_FINE_LOCATION));
		boolean coarseLocationPermission = Boolean.TRUE.equals(result.get(Manifest.permission.ACCESS_COARSE_LOCATION));
		if (fineLocationPermission || coarseLocationPermission) {
			// coarse location accepted
			Util.enableGps(this);
		}

		if (writeStoragePermission) {
			initDb();
		}
	}

	void initDb() {
		appDb = AppDb.getInstance(getApplicationContext());
	}

	void resultActivity(ActivityResult result) {
		if (result.getResultCode() == Activity.RESULT_OK) {
			if (Constant.REQUEST_CODE == Constant.REQUEST_CODE_ENABLE_BLUETOOTH) {
				initBluetooth();
			}
		}
	}

	void checkStatusBt() {
		try {
			new UtilNav<Boolean>().setStateHandle(navController, Constant.BLUETOOTH_CONNECTED, service.getStatus() == BluetoothStatus.CONNECTED);
		} catch (Exception ignored) {
			new UtilNav<Boolean>().setStateHandle(navController, Constant.BLUETOOTH_CONNECTED, false);
		}
	}

	void initBluetooth() {
		config = new BluetoothConfiguration();
		config.context = getApplicationContext();
		config.bluetoothServiceClass = BluetoothClassicService.class;
		config.bufferSize = 1024;
		config.characterDelimiter = '\n';
		config.deviceName = getResources().getString(R.string.app_name);
		config.callListenersInMainThread = false;

		config.uuid = UUID.fromString(Constant.UUID);

		com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService.init(config);
		service = BluetoothService.getDefaultInstance();

		service.setOnEventCallback(this);
		service.setOnScanCallback(this);

		try {
			service.disconnect();
		} catch (Exception ignored) {

		} finally {
			service.startScan();
		}
	}

	void checkBluetoothDevice() {
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (bluetoothAdapter != null) {
			turnOnBT();
		}
	}

	void turnOnBT() {
		if (!bluetoothAdapter.isEnabled()) {
			Constant.REQUEST_CODE = Constant.REQUEST_CODE_ENABLE_BLUETOOTH;
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			mainActivityLauncher.launch(enableBtIntent);
		}
		initBluetooth();
	}

	void print(PaymentStatus paymentStatus) {
		new PrintAsyncTask(this, getApplicationContext()).execute(paymentStatus);
	}

	void observeChild(NavController navController, NavDestination navDestination, Bundle arguments) {
		new UtilNav<>()
				.observeValue(
						navController,
						this,
						Constant.REQUEST_PERMISSION,
						o -> requestPermission());

		new UtilNav<>()
				.observeValue(
						navController,
						this,
						Constant.BLUETOOTH_SCAN_REQUEST,
						o -> checkBluetoothDevice());

		new UtilNav<BluetoothDevice>()
				.observeValue(
						navController,
						this,
						Constant.BLUETOOTH_CONNECT_REQUEST,
						o -> {
							try {
								service.connect(o);
							} catch (Exception ignored) {
							}
						});
		new UtilNav<PaymentStatus>()
				.observeValue(
						navController,
						this,
						Constant.BLUETOOTH_PRINT,
						this::print);
		new UtilNav<>()
				.observeValue(
						navController,
						this,
						Constant.BLUETOOTH_CONNECT_STATUS,
						o -> checkStatusBt());
	}

	public void hideToolbar() {
		getSupportActionBar().hide();
		binding.navView.setVisibility(View.GONE);
		binding.fabScanQr.setVisibility(View.GONE);
	}

	public void showToolbar() {
		getSupportActionBar().show();
		binding.navView.setVisibility(View.VISIBLE);
		binding.fabScanQr.setVisibility(View.VISIBLE);
	}

	@SuppressLint("StaticFieldLeak")
	private static class PrintAsyncTask extends AsyncTask<PaymentStatus, Boolean, Boolean> {
		private final Context context;
		private final ProgressDialog dialog;

		public PrintAsyncTask(Activity activity, Context context) {
			this.context = context;
			dialog = new ProgressDialog(activity);
		}

		@Override
		protected void onPostExecute(Boolean aBoolean) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		}

		@Override
		protected void onPreExecute() {
			dialog.setCancelable(false);
			dialog.setIndeterminate(true);
			dialog.setMessage("Printing");
			dialog.show();
		}

		@Override
		protected Boolean doInBackground(PaymentStatus... printWps) {
			EscPosPrinter printer;
			try {
				PaymentStatus printWp = printWps[0];

				printer = new EscPosPrinter(BluetoothPrintersConnections.selectFirstPaired(), 203, 48f, 32);
				printer.printFormattedText(
						"[C]<b><font size='big'>" + context.getResources().getString(R.string.app_name) + "</font></b>\n" +
								"[C]================================\n" +
								"[L]" + context.getResources().getString(R.string.print_date) + "[R]" + printWp.getTransactionDate() + "\n" +
								"[C]================================\n" +
								"[L]" + context.getResources().getString(R.string.print_npwrd) + "[R]" + printWp.getNpwrd() + "\n" +
								"[L]" + context.getResources().getString(R.string.print_kode_billing) + "[R]" + printWp.getKodeBilling() + "\n" +
								"[L]" + context.getResources().getString(R.string.print_nominal) + "[R]" + printWp.getAmount() + "\n" +
								"[L]" + context.getResources().getString(R.string.print_status) + "[R]" + (printWp.getStatusBayar().equalsIgnoreCase("0")?"Belum Lunas":"Lunas") + "\n" +
								"[C]================================\n" +
								"[C]" + "Terima Kasih" + "\n" +
								"[C]" + "Telah melakukan pembayaran" + "\n" +
								"[C]\n" +
								"[C]\n" +
								"[C]\n" +
								"[C]\n"
				);
			} catch (EscPosConnectionException | EscPosEncodingException | EscPosBarcodeException |
			         EscPosParserException ignored) {
			}
			return false;
		}
	}

}