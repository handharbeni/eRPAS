package io.github.handharbeni.erpas.ui.wp;

import static io.github.handharbeni.erpas.ui.wp.WpSearchFragment.TAG_NPWRD;

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

import io.github.handharbeni.erpas.apis.responses.WP.DataSkrd;
import io.github.handharbeni.erpas.apis.responses.WP.LaporanRealisasi;
import io.github.handharbeni.erpas.apis.responses.WP.ListResponseSkrd;
import io.github.handharbeni.erpas.apis.responses.WP.PaymentStatus;
import io.github.handharbeni.erpas.apis.responses.WP.ResponseWp;
import io.github.handharbeni.erpas.cores.BaseFragment;
import io.github.handharbeni.erpas.databinding.FragmentSkrdDetailBinding;
import io.github.handharbeni.erpas.ui.wp.adapter.DetailWpSkrdAdapter;

public class WpLaporanSkrd extends BaseFragment implements WpModelView.WpCallback,
                                                           DetailWpSkrdAdapter.SkrdCallback {


	public static String KEY_SKRD = "SKRD_REPORT";
	FragmentSkrdDetailBinding binding;
	DetailWpSkrdAdapter adapter;
	View view;
	WpModelView wpModelView;

	ListResponseSkrd listResponseSkrd;
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
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			if (getArguments() != null) {
				listResponseSkrd = getArguments().getSerializable(KEY_SKRD, ListResponseSkrd.class);
				npwrd = getArguments().getString(TAG_NPWRD);
			}
		} else {
			listResponseSkrd = (ListResponseSkrd) getArguments().getSerializable(KEY_SKRD);
			npwrd = getArguments().getString(TAG_NPWRD);
		}

		setupData();
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
	public void onSkrdSuccess(ListResponseSkrd listResponseSkrd) {
		doneLoading();
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
	}

	@Override
	public void onItemClick(DataSkrd dataSkrd) {
		// do some action
	}
}
