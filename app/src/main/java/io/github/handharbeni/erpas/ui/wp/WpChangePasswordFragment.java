package io.github.handharbeni.erpas.ui.wp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import io.github.handharbeni.erpas.apis.responses.WP.LaporanRealisasi;
import io.github.handharbeni.erpas.apis.responses.WP.ListResponseSkrd;
import io.github.handharbeni.erpas.apis.responses.WP.PaymentStatus;
import io.github.handharbeni.erpas.apis.responses.WP.ResponseWp;
import io.github.handharbeni.erpas.cores.BaseFragment;
import io.github.handharbeni.erpas.databinding.FragmentChangePasswordBinding;

public class WpChangePasswordFragment extends BaseFragment implements WpModelView.WpCallback{
	FragmentChangePasswordBinding binding;

	WpModelView wpModelView;
	View view;


	@Nullable
	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState
	) {
		wpModelView = new ViewModelProvider(this).get(WpModelView.class);
		wpModelView.setupClient(requireContext(), this);
		navController = NavHostFragment.findNavController(this);


		binding = FragmentChangePasswordBinding.inflate(inflater, container, false);
		view = binding.getRoot();

		initTrigger();

		return view;
	}

	protected void initTrigger() {
		binding.textInputLayoutPasswordLama.getEditText().addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				int countPasswordBaru = binding.textInputLayoutPasswordBaru.getEditText().getText().length();
				int countPasswordBaruKonfirmasi = binding.textInputLayoutPasswordBaruVerifikasi.getEditText().getText().length();

				binding.btnChange.setEnabled(s.length() >= 1 && countPasswordBaru >= 1 && countPasswordBaruKonfirmasi >= 1);
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		binding.textInputLayoutPasswordBaru.getEditText().addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				int countPasswordLama = binding.textInputLayoutPasswordLama.getEditText().getText().length();
				int countPasswordBaruKonfirmasi = binding.textInputLayoutPasswordBaruVerifikasi.getEditText().getText().length();

				binding.btnChange.setEnabled(s.length() >= 1 && countPasswordLama >= 1 && countPasswordBaruKonfirmasi >= 1);
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		binding.textInputLayoutPasswordBaruVerifikasi.getEditText().addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				int countPasswordLama = binding.textInputLayoutPasswordLama.getEditText().getText().length();
				int countPasswordBaru = binding.textInputLayoutPasswordBaru.getEditText().getText().length();

				binding.btnChange.setEnabled(s.length() >= 1 && countPasswordLama >= 1 && countPasswordBaru >= 1);

				if (!binding.textInputLayoutPasswordBaru.getEditText().getText().toString().equalsIgnoreCase(String.valueOf(s))) {
					binding.textInputLayoutPasswordBaruVerifikasi.setError("Password Tidak Sama");
					binding.btnChange.setEnabled(false);
				} else {
					binding.textInputLayoutPasswordBaruVerifikasi.setError(null);
					binding.btnChange.setEnabled(true);
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		binding.btnChange.setOnClickListener(v -> wpModelView.changePassword(
				binding.textInputLayoutPasswordLama.getEditText().getText().toString(),
				binding.textInputLayoutPasswordBaru.getEditText().getText().toString()
		));
	}

	@Override
	public void onLoad() {
		showLoading();
	}

	@Override
	public void onSuccess(ResponseWp responseWp) {

	}

	@Override
	public void onPaymentSuccess(PaymentStatus paymentStatus) {

	}

	@Override
	public void onSuccessTutup() {

	}

	@Override
	public void onSuccessChangePassword() {
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

	}

	@Override
	public void onFailed(String message) {
		doneLoading();
		Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
	}

	public void showLoading() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
		progressDialog = new ProgressDialog(requireActivity());
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
		progressDialog.setMessage("Tunggu Sebentar");
		progressDialog.show();
	}

	public void doneLoading() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}
}
