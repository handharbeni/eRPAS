package io.github.handharbeni.erpas.ui.help;

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
import io.github.handharbeni.erpas.databinding.FragmentHelpBinding;

public class HelpFragment extends BaseFragment {
	FragmentHelpBinding binding;
	View root;
	HelpViewModel helpViewModel;

	@Nullable
	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState
	) {
		Log.d(TAG, "onCreateView: Help");
		helpViewModel = new ViewModelProvider(this).get(HelpViewModel.class);
		binding = FragmentHelpBinding.inflate(inflater, container, false);

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
