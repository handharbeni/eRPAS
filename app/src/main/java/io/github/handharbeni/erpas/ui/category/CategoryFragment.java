package io.github.handharbeni.erpas.ui.category;

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
import io.github.handharbeni.erpas.databinding.FragmentCategoryBinding;

public class CategoryFragment extends BaseFragment {
	FragmentCategoryBinding binding;


	@Nullable
	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState
	) {
		Log.d(TAG, "onCreateView: Category");
		CategoryViewModel categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
		binding = FragmentCategoryBinding.inflate(inflater, container, false);
		View root = binding.getRoot();

		navController = NavHostFragment.findNavController(this);

		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}
