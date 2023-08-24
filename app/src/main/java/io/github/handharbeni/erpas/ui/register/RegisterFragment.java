package io.github.handharbeni.erpas.ui.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import io.github.handharbeni.erpas.R;
import io.github.handharbeni.erpas.cores.BaseFragment;
import io.github.handharbeni.erpas.databinding.FragmentRegisterBinding;

public class RegisterFragment extends BaseFragment implements RegisterViewModel.RegisterCallback {
	FragmentRegisterBinding binding;
	View view;
	RegisterViewModel registerViewModel;

	@Nullable
	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState
	) {
		navController = NavHostFragment.findNavController(this);

		registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
		registerViewModel.setupClient(requireContext(), this);

		binding = FragmentRegisterBinding.inflate(inflater, container, false);
		view = binding.getRoot();

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
		binding.btnRegister.setOnClickListener(v -> {
			String userName = binding.txtUsername.getEditText().getText().toString();
			String fullName = binding.txtFullname.getEditText().getText().toString();
			String email = binding.txtEmail.getEditText().getText().toString();
			String password = binding.txtPassword.getEditText().getText().toString();

			registerViewModel.doRegister(userName, fullName, email, password);
		});

		binding.txtToLogin.setOnClickListener(v -> {
			navController.navigate(R.id.action_registerFragment_to_loginFragment);
		});
	}

	@Override
	public void onRegister() {
		showLoading();
	}

	@Override
	public void onRegisterSuccess() {
		doneLoading();
		// back to login screen
		navController.navigate(R.id.action_registerFragment_to_loginFragment);
	}

	@Override
	public void onRegisterFailed(String message) {
		doneLoading();
		// show error message
	}
}
