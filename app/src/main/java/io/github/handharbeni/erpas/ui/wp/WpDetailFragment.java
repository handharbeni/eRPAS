package io.github.handharbeni.erpas.ui.wp;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import io.github.handharbeni.erpas.R;
import io.github.handharbeni.erpas.apis.responses.WP.DataTagihan;
import io.github.handharbeni.erpas.apis.responses.WP.DataWp;
import io.github.handharbeni.erpas.apis.responses.WP.LaporanRealisasi;
import io.github.handharbeni.erpas.apis.responses.WP.ListResponseSkrd;
import io.github.handharbeni.erpas.apis.responses.WP.PaymentStatus;
import io.github.handharbeni.erpas.apis.responses.WP.ResponseWp;
import io.github.handharbeni.erpas.cores.BaseFragment;
import io.github.handharbeni.erpas.databinding.FragmentDetailWpBinding;
import io.github.handharbeni.erpas.ui.wp.adapter.DetailWpAdapter;

public class WpDetailFragment extends BaseFragment
		implements WpModelView.WpCallback, DetailWpAdapter.DetailWpCallback {

	public static String KEY_WP = "DETAIL_WP";
	FragmentDetailWpBinding binding;
	DetailWpAdapter detailWpAdapter;
	View view;
	WpModelView wpModelView;

	ResponseWp responseWp;

	@Nullable
	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState
	) {
		wpModelView = new ViewModelProvider(this).get(WpModelView.class);
		wpModelView.setupClient(requireContext(), this);

		binding = FragmentDetailWpBinding.inflate(inflater, container, false);
		view = binding.getRoot();

		navController = NavHostFragment.findNavController(this);

		setupAdapter();

		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			if (getArguments() != null) {
				responseWp = getArguments().getSerializable(KEY_WP, ResponseWp.class);
			}
		} else {
			responseWp = (ResponseWp) getArguments().getSerializable(KEY_WP);
		}

		setupData();
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

	}

	@Override
	public void onSuccessTutup() {
		doneLoading();
		navController.navigateUp();
	}

	@Override
	public void onSkrdSuccess(ListResponseSkrd listResponseSkrd) {

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
	}

	void setupAdapter() {
		LinearLayoutManager llm = new LinearLayoutManager(requireContext());
		detailWpAdapter = new DetailWpAdapter(requireContext(), new ArrayList<>(), this);

		binding.rvItemWp.setLayoutManager(llm);
		binding.rvItemWp.setAdapter(detailWpAdapter);
	}

	void setupData() {
		detailWpAdapter.updateData(
				responseWp.getDataTagihan()!=null?
						responseWp.getDataTagihan()
						:new ArrayList<>());

		DataWp dataWp = responseWp.getDataWp();

		binding.txtNpwrd.setText(String.format("Nomor NPWRD: %s", dataWp.getNpwrd()));
		binding.txtNama.setText(String.format("Nama Wajib Pajak: %s", dataWp.getNmWpWr()));
		binding.txtAlamat.setText(String.format("Alamat Wajib Pajak: %s", dataWp.getAlamatWpWr()));
		binding.txtKota.setText(String.format("Kota: %s", dataWp.getKota()));
		binding.txtNominal.setText(String.format("Nominal Pengenaan: %s", dataWp.getNominal()));
		binding.btnTutup.setOnClickListener(v -> wpModelView.kiosTutup(dataWp.getNpwrd(), "0"));
		binding.btnQris.setOnClickListener(v -> {
			DataTagihan dataTagihan = new DataTagihan();
			dataTagihan.setKdRekening(dataWp.getNpwrd());
			dataTagihan.setTotalRetribusi(dataWp.getNominal());
			onQrisClick(dataTagihan);
		});
	}

	@Override
	public void onCashClick(DataTagihan dataTagihan) {

	}

	@Override
	public void onQrisClick(DataTagihan dataTagihan) {
		Bundle bundle = new Bundle();
		bundle.putSerializable(KEY_WP, dataTagihan);
		navController.navigate(R.id.action_navigation_detail_wp_to_navigation_detail_qris_wp, bundle);
	}

	@Override
	public void onItemClick(DataTagihan dataTagihan) {

	}
}
