package io.github.handharbeni.erpas.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import io.github.handharbeni.erpas.R;
import io.github.handharbeni.erpas.cores.BaseFragment;
import io.github.handharbeni.erpas.databinding.FragmentLoginBinding;
import io.github.handharbeni.erpas.ui.home.adapter.MenuAdapter;

public class LoginFragment extends BaseFragment
		implements MenuAdapter.MenuCallback, LoginViewModel.LoginCallback {
	FragmentLoginBinding binding;
	View view;

	LoginViewModel loginViewModel;

	@Nullable
	@Override
	public android.view.View onCreateView(
			@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState
	) {
		loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
		loginViewModel.setupClient(requireContext(), this);

		binding = FragmentLoginBinding.inflate(inflater, container, false);
		view = binding.getRoot();

		navController = NavHostFragment.findNavController(this);

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		hideToolbar(requireActivity());
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setupTrigger();
	}

	void setupTrigger() {
		binding.btnLogin.setOnClickListener(v -> loginViewModel.doLogin());
	}

	@Override
	public void onItemClick(String menu) {

	}

	@Override
	public void onLogin() {
		showLoading();
	}

	@Override
	public void onLoginSuccess() {
		doneLoading();

		navController.navigate(R.id.action_loginFragment_to_navigation_home);
	}

	@Override
	public void onLoginFailed() {
		doneLoading();
	}

	@Override
	public void onLoginExpired() {
		doneLoading();
	}
}
