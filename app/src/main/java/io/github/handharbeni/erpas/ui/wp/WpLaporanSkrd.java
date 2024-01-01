package io.github.handharbeni.erpas.ui.wp;

import static io.github.handharbeni.erpas.ui.wp.WpSearchFragment.TAG_NPWRD;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import io.github.handharbeni.erpas.R;
import io.github.handharbeni.erpas.apis.responses.WP.DataSkrd;
import io.github.handharbeni.erpas.apis.responses.WP.DataTagihan;
import io.github.handharbeni.erpas.apis.responses.WP.LaporanRealisasi;
import io.github.handharbeni.erpas.apis.responses.WP.ListResponseSkrd;
import io.github.handharbeni.erpas.apis.responses.WP.PaymentStatus;
import io.github.handharbeni.erpas.apis.responses.WP.ResponseWp;
import io.github.handharbeni.erpas.cores.BaseFragment;
import io.github.handharbeni.erpas.databinding.FragmentSkrdDetailBinding;
import io.github.handharbeni.erpas.ui.wp.adapter.DetailWpSkrdAdapter;
import io.github.handharbeni.erpas.utils.Constant;

public class WpLaporanSkrd extends BaseFragment
		implements WpModelView.WpCallback,
		           DetailWpSkrdAdapter.SkrdCallback {


	public static String KEY_SKRD = "SKRD_REPORT";
	FragmentSkrdDetailBinding binding;
	DetailWpSkrdAdapter adapter;
	View view;
	WpModelView wpModelView;

	ListResponseSkrd listResponseSkrd;

	ListResponseSkrd tempListResponseSkrd;
	String npwrd;

	@Nullable
	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState
	) {
		wpModelView = new ViewModelProvider(this).get(WpModelView.class);
		wpModelView.setupClient(requireContext(), this);

		binding = FragmentSkrdDetailBinding.inflate(inflater, container, false);
		view = binding.getRoot();

		navController = NavHostFragment.findNavController(this);
		setupAdapter();

		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		loadData();

		binding.selectStatusBayar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (position == 0) {
					// semua
					try {
						adapter.filterStatus("-1");
					} catch (Exception ignored) {}
				} else if (position == 1) {
					try {
						adapter.filterStatus("1");
					} catch (Exception ignored) {}
				} else if (position == 2) {
					try {
						adapter.filterStatus("0");
					} catch (Exception ignored) {}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}


	void loadData() {
		wpModelView.skrdReport("id_user", utilDb.getString("IdUser"));
	}


	void setupAdapter() {
		adapter = new DetailWpSkrdAdapter(requireContext(), new ArrayList<>(), this);
		LinearLayoutManager llm = new LinearLayoutManager(requireContext());
		binding.rvItemWp.setLayoutManager(llm);
		binding.rvItemWp.setAdapter(adapter);
	}

	void setupData() {
		binding.txtNpwrd.setText(String.format("NPWRD: %s", npwrd));
		if (listResponseSkrd.getDataSkrd() != null) {
			adapter.updateData(listResponseSkrd.getDataSkrd());
		}
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
		doneLoading();
	}

	@Override
	public void onSuccessTutup() {
		doneLoading();
	}

	@Override
	public void onSuccessChangePassword() {

	}

	@Override
	public void onSkrdSuccess(ListResponseSkrd listResponseSkrd) {
		doneLoading();
		this.listResponseSkrd = listResponseSkrd;
		setupData();

		binding.rvItemWp.setVisibility(View.VISIBLE);
		binding.txtNoData.setVisibility(View.GONE);
	}

	@Override
	public void onRealisasiSuccess(LaporanRealisasi laporanRealisasi) {
		doneLoading();
	}

	@Override
	public void onSuccessQris(String qris) {
		doneLoading();
	}

	@Override
	public void onFailed(String message) {
		doneLoading();
		binding.rvItemWp.setVisibility(View.GONE);
		binding.txtNoData.setVisibility(View.VISIBLE);
	}

	@Override
	public void onItemClick(DataSkrd dataSkrd) {
		// do some action
	}

	@Override
	public void onQrisClick(DataSkrd dataSkrd) {
		// do some action
		DataTagihan dataTagihan = new DataTagihan();
		dataTagihan.setKdRekening(dataSkrd.getNpwrd());
		dataTagihan.setTotalRetribusi(dataSkrd.getTotalRetribusi());

		Bundle bundle = new Bundle();
		bundle.putSerializable(WpDetailFragment.KEY_WP, dataTagihan);

		navController.navigate(R.id.action_wpLaporanSkrd_to_navigation_detail_qris_wp2, bundle);
	}

	@Override
	public void onPrintClick(DataSkrd dataSkrd) {
		PaymentStatus paymentStatus = new PaymentStatus();
		paymentStatus.setAmount(dataSkrd.getTotalRetribusi());
		paymentStatus.setTransactionDate(dataSkrd.getTglSkrd());
		paymentStatus.setNpwrd(dataSkrd.getNpwrd());
		paymentStatus.setKodeBilling(dataSkrd.getKodeBilling());
		paymentStatus.setStatusBayar(dataSkrd.getStatusBayar());
		paymentStatus.setStatus(dataSkrd.getStatusKetetapan());

		setState(Constant.BLUETOOTH_PRINT, paymentStatus);
	}
}
