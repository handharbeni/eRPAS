package io.github.handharbeni.erpas.ui.wp;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import io.github.handharbeni.erpas.apis.responses.WP.DataTagihan;
import io.github.handharbeni.erpas.apis.responses.WP.LaporanRealisasi;
import io.github.handharbeni.erpas.apis.responses.WP.ListResponseSkrd;
import io.github.handharbeni.erpas.apis.responses.WP.PaymentStatus;
import io.github.handharbeni.erpas.apis.responses.WP.ResponseWp;
import io.github.handharbeni.erpas.cores.BaseFragment;
import io.github.handharbeni.erpas.databinding.FragmentDetailWpQrisBinding;
import io.github.handharbeni.erpas.utils.Constant;

public class WpDetailQrisFragment extends BaseFragment implements WpModelView.WpCallback {
	FragmentDetailWpQrisBinding binding;
	WpModelView wpModelView;
    View view;

	DataTagihan dataTagihan;

	PaymentStatus paymentStatus;

	private int mInterval = 5000; // 5 seconds by default, can be changed later
	private Handler mHandler;

	Runnable mStatusChecker = () -> {
		try {
			// do check status
			checkStatus();
		} catch (Exception ignored) {
		}
	};

	@Nullable
	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState
	) {
		wpModelView = new ViewModelProvider(this).get(WpModelView.class);
		wpModelView.setupClient(requireContext(), this);
		navController = NavHostFragment.findNavController(this);

		mHandler = new Handler();

		binding = FragmentDetailWpQrisBinding.inflate(inflater, container, false);
		view = binding.getRoot();

		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			if (getArguments() != null) {
				dataTagihan = (DataTagihan) getArguments().getSerializable(WpDetailFragment.KEY_WP, DataTagihan.class);
			}
		} else {
			if (getArguments() != null) {
				dataTagihan = (DataTagihan) getArguments().getSerializable(WpDetailFragment.KEY_WP);
			}
		}
		if (dataTagihan != null) {
			wpModelView.fetchQris(dataTagihan.getKdRekening(), dataTagihan.getTotalRetribusi());
		} else {
			navController.navigateUp();
		}
	}

	public void bindData(String qris) {
		binding.txtIdPayment.setText(dataTagihan.getKdRekening());
		generateQr(binding.ivQris, qris);
	}

	public void setupListener() {
		binding.btnOther.setOnClickListener(v -> navController.navigateUp());
		binding.btnPrint.setOnClickListener(v -> {
			if (this.paymentStatus != null) {
				setState(Constant.BLUETOOTH_PRINT, paymentStatus);
			}
		});
	}

	@Override
	public void onLoad() {
		showLoading();
	}

	@Override
	public void onSuccess(ResponseWp responseWp) {
		doneLoading();
	}

	@Override
	public void onPaymentSuccess(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;

		binding.btnPrint.setVisibility(View.VISIBLE);
		binding.btnOther.setVisibility(View.GONE);

		stopRepeatingTask();
//		setState(Constant.BLUETOOTH_PRINT, paymentStatus);
//		navController.navigateUp();
	}

	@Override
	public void onSuccessTutup() {
		doneLoading();
	}

	@Override
	public void onSkrdSuccess(ListResponseSkrd listResponseSkrd) {

	}

	@Override
	public void onRealisasiSuccess(LaporanRealisasi laporanRealisasi) {

	}

	@Override
	public void onSuccessQris(String qris) {
		binding.btnOther.setVisibility(View.GONE);

		binding.txtIdStatus.setText("Status Pembayaran: Telah diterima");

		doneLoading();
		bindData(qris);
		setupListener();

		startRepeatingTask();
	}

	@Override
	public void onFailed(String message) {
		doneLoading();
		startRepeatingTask();
	}

	private void checkStatus() {
		wpModelView.checkPayment(dataTagihan.getKdRekening(), dataTagihan.getTotalRetribusi());
	}


	void startRepeatingTask() {
		mHandler.postDelayed(mStatusChecker, mInterval);
	}

	void stopRepeatingTask() {
		mHandler.removeCallbacks(mStatusChecker);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		stopRepeatingTask();
	}


}
