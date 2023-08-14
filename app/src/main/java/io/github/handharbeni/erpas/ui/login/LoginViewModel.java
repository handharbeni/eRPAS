package io.github.handharbeni.erpas.ui.login;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import io.github.handharbeni.erpas.apis.Client;
import io.github.handharbeni.erpas.apis.ClientInterface;
import io.github.handharbeni.erpas.cores.BaseModelView;

public class LoginViewModel extends BaseModelView {
	ClientInterface client;
	LoginCallback loginCallback;
	public void setupClient(Context context, LoginCallback loginCallback) {
		client = Client.getInstance(context, ClientInterface.class);
		this.loginCallback = loginCallback;
	}


	public LoginViewModel() {
	}

	public void doLogin() {
		this.loginCallback.onLogin();
		this.loginCallback.onLoginSuccess();
	}

	public interface LoginCallback {
		void onLogin();
		void onLoginSuccess();
		void onLoginFailed();
		void onLoginExpired();
	}
}
