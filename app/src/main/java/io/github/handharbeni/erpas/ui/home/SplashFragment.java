package io.github.handharbeni.erpas.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import com.airbnb.lottie.LottieDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.github.handharbeni.erpas.R;
import io.github.handharbeni.erpas.cores.BaseFragment;
import io.github.handharbeni.erpas.databinding.FragmentSplashBinding;
import io.github.handharbeni.erpas.utils.Constant;

public class SplashFragment extends BaseFragment {
	FragmentSplashBinding binding;
	View view;


	@Nullable
	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState
	) {
		binding = FragmentSplashBinding.inflate(inflater, container, false);
		view = binding.getRoot();

		navController = NavHostFragment.findNavController(this);

		if (utilDb.getBoolean(Constant.ISLOGGEDIN)) {
			new Handler().postDelayed(() -> {
				// goto home page
				navController.navigate(R.id.action_splashFragment_to_navigation_home);
			}, 5000);
		} else {
			new Handler().postDelayed(() -> {
				// goto login page
				navController.navigate(R.id.action_splashFragment_to_loginFragment);
			}, 5000);
		}

		return view;
	}


	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		binding.ivLogo.setAnimation(R.raw.cart_load);
		binding.ivLogo.setRepeatMode(LottieDrawable.RESTART);
		binding.ivLogo.loop(true);
		binding.ivLogo.playAnimation();
	}

	@Override
	public void onResume() {
		super.onResume();
		hideToolbar(requireActivity());
	}
}
