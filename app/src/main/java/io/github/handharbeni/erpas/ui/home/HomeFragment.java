package io.github.handharbeni.erpas.ui.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.HashMap;
import java.util.Map;

import io.github.handharbeni.erpas.MainActivity;
import io.github.handharbeni.erpas.R;
import io.github.handharbeni.erpas.cores.BaseFragment;
import io.github.handharbeni.erpas.databinding.FragmentHomeBinding;
import io.github.handharbeni.erpas.ui.home.adapter.MenuAdapter;

public class HomeFragment extends BaseFragment implements MenuAdapter.MenuCallback {


	public static String TAG_PAYMENT = "PAYMENT";
	public static String TAG_SKRD = "SKRD";
	public static String TAG_PASAR = "PASAR";
	public static String TAG_REALISASI = "REALISASI";
	private FragmentHomeBinding binding;
	private HomeViewModel homeViewModel;

	public View onCreateView(
			@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState
	) {
		homeViewModel =
				new ViewModelProvider(this).get(HomeViewModel.class);
		homeViewModel.setupData(requireContext());

		binding = FragmentHomeBinding.inflate(inflater, container, false);
		View root = binding.getRoot();
		navController = NavHostFragment.findNavController(this);
		setHasOptionsMenu(true);
		return root;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setupAdapter();
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		inflater.inflate(R.menu.toolbar_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {

		if (item.getItemId() == R.id.menuLogout) {
			logout();
			navigate(R.id.action_navigation_home_to_loginFragment);
			hideToolbar(requireActivity());
		} else if (item.getItemId() == R.id.menuGantiPassword) {
			navigate(R.id.action_navigation_home_to_wpChangePasswordFragment);
			hideToolbar(requireActivity());
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onResume() {
		super.onResume();
		showToolbar(requireActivity());
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

	void setupAdapter() {
		GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
		binding.rvListMenu.setLayoutManager(gridLayoutManager);

		MenuAdapter menuAdapter = new MenuAdapter(requireContext(), homeViewModel.getMenu()
		                                                                         .getValue(), this);
		binding.rvListMenu.setAdapter(menuAdapter);
	}

	@Override
	public void onItemClick(String menu) {
		Bundle bundle = new Bundle();
		if (menu.equalsIgnoreCase("Pembayaran")) {
			MainActivity.isInBackStack = true;
			bundle.putBoolean(TAG_PAYMENT, true);
			navController.navigate(R.id.action_navigation_home_to_navigation_search_wp, bundle);
		} else if (menu.equalsIgnoreCase("SKRD")) {
			bundle.putBoolean(TAG_SKRD, true);
			MainActivity.isInBackStack = true;
			navController.navigate(R.id.action_navigation_home_to_wpLaporanSkrd, bundle);
		} else if (menu.equalsIgnoreCase("Data Pasar")) {
			MainActivity.isInBackStack = true;
		} else if (menu.equalsIgnoreCase("Realisasi")) {
			MainActivity.isInBackStack = true;
			navController.navigate(R.id.action_navigation_home_to_wpRealisasiReport);
		}
	}
}