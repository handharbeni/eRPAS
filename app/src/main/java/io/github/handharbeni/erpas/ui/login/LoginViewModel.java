package io.github.handharbeni.erpas.ui.login;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.json.JSONObject;

import java.util.HashMap;

import io.github.handharbeni.erpas.apis.Client;
import io.github.handharbeni.erpas.apis.ClientInterface;
import io.github.handharbeni.erpas.apis.responses.WP.ResponseLogin;
import io.github.handharbeni.erpas.cores.BaseModelView;
import io.github.handharbeni.erpas.utils.Constant;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends BaseModelView {
	@SuppressLint("StaticFieldLeak")
	protected Context context;
	protected ClientInterface client;
	protected LoginCallback loginCallback;
	public void setupClient(Context context, LoginCallback loginCallback) {
		this.context = context;
		client = Client.getInstance(context, ClientInterface.class);
		this.loginCallback = loginCallback;
	}


	public LoginViewModel() {
	}

	public void doLogin(String username, String password) {
		this.loginCallback.onLogin();

		HashMap<String, String> dataLogin = new HashMap<>();
		dataLogin.put("username", username);
		dataLogin.put("password", password);

		JSONObject jsonObject = new JSONObject(dataLogin);

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);


		client.login(body).enqueue(new Callback<ResponseLogin>() {
			@Override
			public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
				getDb(context).putBoolean(Constant.ISLOGGEDIN, false);
				if (response.isSuccessful()) {
					if (response.body() != null) {
						if (response.body().getStatus().equals("sukses")) {
							// login sukses
							getDb(context).putString("Username", response.body().getUsername());
							getDb(context).putString("Fullname", response.body().getFullname());
							getDb(context).putString("Email", response.body().getEmail());
							getDb(context).putBoolean(Constant.ISLOGGEDIN, true);
							loginCallback.onLoginSuccess();
						} else {
							// login failed
							loginCallback.onLoginFailed();
						}
					} else {
						loginCallback.onLoginFailed();
					}
				} else {
					loginCallback.onLoginFailed();
				}
			}

			@Override
			public void onFailure(Call<ResponseLogin> call, Throwable t) {
				getDb(context).putBoolean(Constant.ISLOGGEDIN, false);
				loginCallback.onLoginFailed();
			}
		});

	}

	public interface LoginCallback {
		void onLogin();
		void onLoginSuccess();
		void onLoginFailed();
		void onLoginExpired();
	}
}
