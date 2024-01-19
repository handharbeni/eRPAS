package io.github.handharbeni.erpas.cores;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.Random;

import io.github.handharbeni.erpas.databinding.CustomScannerBinding;

public class ScannerActivity extends BaseActivity implements
                                                  DecoratedBarcodeView.TorchListener{

	CustomScannerBinding binding;
	private CaptureManager capture;
//	private DecoratedBarcodeView barcodeScannerView;
//	private Button switchFlashlightButton;
//	private ViewfinderView viewfinderView;


	@Override
	public void onCreate(
			@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = CustomScannerBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		binding.barcodeScanner.setTorchListener(this);
		if (!hasFlash()) {
			binding.switchFlashlight.setVisibility(View.GONE);
		}

		capture = new CaptureManager(this, binding.barcodeScanner);
		capture.initializeFromIntent(getIntent(), savedInstanceState);
		capture.setShowMissingCameraPermissionDialog(true);
		capture.decode();

		changeMaskColor(null);
		changeLaserVisibility(true);
	}

	@Override
	protected void onResume() {
		super.onResume();
		try {
			capture.onResume();
		} catch (Exception ignored) {}
	}

	@Override
	protected void onPause() {
		super.onPause();
		try {
			capture.onPause();
		} catch (Exception ignored) {}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			capture.onDestroy();
		} catch (Exception ignored) {}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		try {
			capture.onSaveInstanceState(outState);
		} catch (Exception ignored) {}
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		try {
			return binding.barcodeScanner.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
		} catch (Exception ignored) {
			return false;
		}
	}

	/**
	 * Check if the device's camera has a Flashlight.
	 * @return true if there is Flashlight, otherwise false.
	 */
	private boolean hasFlash() {
		return getApplicationContext().getPackageManager()
		                              .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
	}

	public void switchFlashlight(View view) {
//		if (getString(R.string.turn_on_flashlight).equals(switchFlashlightButton.getText())) {
//			barcodeScannerView.setTorchOn();
//		} else {
//			barcodeScannerView.setTorchOff();
//		}
	}

	public void changeMaskColor(View view) {
		Random rnd = new Random();
		int color = Color.argb(100, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		binding.barcodeScanner.getViewFinder().setMaskColor(color);
	}

	public void changeLaserVisibility(boolean visible) {
		binding.barcodeScanner.getViewFinder().setLaserVisibility(visible);
	}

	@Override
	public void onTorchOn() {

	}

	@Override
	public void onTorchOff() {

	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		capture.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}
}
