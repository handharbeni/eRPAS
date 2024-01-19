package io.github.handharbeni.erpas.ui.wp;

import static io.github.handharbeni.erpas.ui.home.HomeFragment.TAG_PAYMENT;
import static io.github.handharbeni.erpas.ui.home.HomeFragment.TAG_SKRD;
import static io.github.handharbeni.erpas.ui.wp.WpDetailFragment.KEY_WP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;

import io.github.handharbeni.erpas.R;
import io.github.handharbeni.erpas.apis.responses.WP.DataTagihan;
import io.github.handharbeni.erpas.apis.responses.WP.DataWp;
import io.github.handharbeni.erpas.apis.responses.WP.LaporanRealisasi;
import io.github.handharbeni.erpas.apis.responses.WP.ListResponseSkrd;
import io.github.handharbeni.erpas.apis.responses.WP.PaymentStatus;
import io.github.handharbeni.erpas.apis.responses.WP.ResponseWp;
import io.github.handharbeni.erpas.cores.BaseFragment;
import io.github.handharbeni.erpas.cores.ScannerActivity;
import io.github.handharbeni.erpas.databinding.FragmentSearchWpBinding;
import io.github.handharbeni.erpas.utils.UtilDate;

public class WpSearchFragment extends BaseFragment implements WpModelView.WpCallback {


	public static String TAG_NPWRD = "io.github.handharbeni.erpas.ui.wp.NPWRD";
	WpModelView wpModelView;
	FragmentSearchWpBinding binding;
	View view;


	public static String DIRECT_SCAN = "io.github.handharbeni.erpas.ui.wp.DIRECT_SCAN";

	boolean directScan = false;
	boolean payment = false;
	boolean skrd = false;
	private ResponseWp responseWp;


	@Nullable
	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState
	) {
		wpModelView = new ViewModelProvider(this).get(WpModelView.class);
		wpModelView.setupClient(requireContext(), this);
		binding = FragmentSearchWpBinding.inflate(inflater, container, false);
		view = binding.getRoot();
		navController = NavHostFragment.findNavController(this);
		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		try {
			if (getArguments() != null) {
				directScan = getArguments().getBoolean(DIRECT_SCAN);
				payment = getArguments().getBoolean(TAG_PAYMENT);
				skrd = getArguments().getBoolean(TAG_SKRD);
			}
		} catch (Exception ignored) {}


		binding.btnSearch.setOnClickListener(v -> {
			if (payment) {
				wpModelView.fetchWp(binding.edtNpwrd.getText().toString());
			} else if (skrd) {
				wpModelView.skrdReport(binding.edtNpwrd.getText().toString());
			}
		});
		binding.ivScan.setOnClickListener(this::scanQr);

		if (directScan) {
			scanQr(view);
		}
	}

	@Override
	public void onLoad() {
		showLoading();
	}

	@Override
	public void onSuccess(ResponseWp responseWp) {
		doneLoading();
		this.responseWp = responseWp;
		setupNpwrdData();
		binding.svDetailNpwrd.setVisibility(View.VISIBLE);
//		Bundle bundle = new Bundle();
//		bundle.putSerializable(WpDetailFragment.KEY_WP, responseWp);

//		navController.navigate(R.id.action_navigation_search_wp_to_navigation_detail_wp, bundle);
	}

	@Override
	public void onPaymentSuccess(PaymentStatus paymentStatus) {

	}

	@Override
	public void onSuccessTutup() {
		doneLoading();
		navController.navigateUp();
	}

	@Override
	public void onSuccessChangePassword() {

	}

	@Override
	public void onSkrdSuccess(ListResponseSkrd listResponseSkrd) {
		doneLoading();
		Bundle bundle = new Bundle();
		bundle.putSerializable(WpLaporanSkrd.KEY_SKRD, listResponseSkrd);
		bundle.putString(TAG_NPWRD, binding.edtNpwrd.getText().toString());

		navController.navigate(R.id.action_navigation_search_wp_to_wpLaporanSkrd, bundle);
	}

	@Override
	public void onRealisasiSuccess(LaporanRealisasi laporanRealisasi) {

	}

	@Override
	public void onSuccessQris(String qris) {
		doneLoading();
	}

	@Override
	public void onFailed(String message) {
		doneLoading();
		showDialogOneButton("Error", message, "Dismiss");
	}

	void scanQr(View view) {
//		ScanOptions options = new ScanOptions();
//		options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
//		options.setOrientationLocked(false);
//		options.setPrompt("Kios QRCODE");
//		options.setCameraId(0);
//		options.setBeepEnabled(true);
//		options.setBarcodeImageEnabled(true);
//
//		barcodeLauncher.launch(options);
		ScanOptions options = new ScanOptions()
				.setOrientationLocked(true)
				.setCaptureActivity(ScannerActivity.class)
				.setCameraId(0)
				.setBeepEnabled(false)
				.setBarcodeImageEnabled(true)
				.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
		barcodeLauncher.launch(options);

	}

	void setupNpwrdData() {
		checkQris();
		DataWp dataWp = responseWp.getDataWp();

		binding.txtNpwrd.setText(dataWp.getNpwrd());
		binding.txtNama.setText(dataWp.getNmWpWr());
		binding.txtAlamat.setText(dataWp.getAlamatWpWr());
		binding.txtKota.setText(dataWp.getKota());
		binding.txtNominal.setText(dataWp.getNominal());
		binding.btnTutup.setOnClickListener(v -> wpModelView.kiosTutup(dataWp.getNpwrd(), dataWp.getNominal()));
		binding.btnQris.setOnClickListener(v -> {
			String date = UtilDate.longToDate(System.currentTimeMillis(), "M");
			utilDb.putBoolean(date+"-"+dataWp.getNpwrd(), true);
			binding.btnTutup.setEnabled(false);

			DataTagihan dataTagihan = new DataTagihan();
			dataTagihan.setKdRekening(dataWp.getNpwrd());
			dataTagihan.setTotalRetribusi(dataWp.getNominal());

			onQrisClick(dataTagihan);
		});
	}

	void checkQris() {
		String date = UtilDate.longToDate(System.currentTimeMillis(), "M");
		boolean hasQris = utilDb.getBoolean(date+"-"+ responseWp.getDataWp().getNpwrd());
		binding.btnTutup.setEnabled(!hasQris);
	}

	public void onQrisClick(DataTagihan dataTagihan) {
		Bundle bundle = new Bundle();
		bundle.putSerializable(KEY_WP, dataTagihan);
		navController.navigate(R.id.action_navigation_search_wp_to_navigation_detail_qris_wp, bundle);
	}


	// Register the launcher and result handler
	private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(
			new ScanContract(),
			result -> {
				if (result.getContents() == null) {
					Toast.makeText(requireActivity(), "Nothing Scanned", Toast.LENGTH_SHORT).show();
				} else {
					binding.edtNpwrd.setText(result.getContents());
					if (payment) {
						wpModelView.fetchWp(result.getContents());
					} else if (skrd) {
						wpModelView.skrdReport(result.getContents());
					}
				}
			}
	);
}
