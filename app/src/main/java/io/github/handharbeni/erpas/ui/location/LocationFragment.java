package io.github.handharbeni.erpas.ui.location;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import io.github.handharbeni.erpas.cores.BaseFragment;
import io.github.handharbeni.erpas.databinding.FragmentLocationBinding;

public class LocationFragment extends BaseFragment {
	FragmentLocationBinding binding;
	LocationViewModel locationViewModel;
	View root;

	@Nullable
	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState
	) {
		Log.d(TAG, "onCreateView: Location");
		locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);
		binding = FragmentLocationBinding.inflate(inflater, container, false);
		root = binding.getRoot();

		navController = NavHostFragment.findNavController(this);

		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}
