package io.github.handharbeni.erpas.ui.register;

import android.annotation.SuppressLint;
import android.content.Context;

import org.json.JSONObject;

import java.util.HashMap;

import io.github.handharbeni.erpas.apis.Client;
import io.github.handharbeni.erpas.apis.ClientInterface;
import io.github.handharbeni.erpas.apis.responses.WP.ResponseRegister;
import io.github.handharbeni.erpas.cores.BaseModelView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends BaseModelView {
	@SuppressLint("StaticFieldLeak")
	protected Context context;
	protected ClientInterface client;
	protected RegisterCallback registerCallback;

	public void setupClient(Context context, RegisterCallback registerCallback) {
		this.context = context;
		client = Client.getInstance(context, ClientInterface.class);
		this.registerCallback = registerCallback;
	}

	public RegisterViewModel() {
	}

	public void doRegister(String userName, String fullName, String email, String password) {
		this.registerCallback.onRegister();

		HashMap<String, String> dataRegister = new HashMap<>();
		dataRegister.put("username", userName);
		dataRegister.put("fullname", fullName);
		dataRegister.put("email", email);
		dataRegister.put("password", password);

		JSONObject jsonObject = new JSONObject(dataRegister);

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);

		client.register(body).enqueue(new Callback<ResponseRegister>() {
			@Override
			public void onResponse(
					Call<ResponseRegister> call, Response<ResponseRegister> response
			) {
				if (response.isSuccessful()) {
					if (response.body() != null) {
						if (response.body().getStatus().equalsIgnoreCase("sukses")) {
							registerCallback.onRegisterSuccess();
						} else {
							registerCallback.onRegisterFailed("Register Failed");
						}
					} else {
						registerCallback.onRegisterFailed("Register Failed");
					}
				} else {
					registerCallback.onRegisterFailed("Register Failed");
				}
			}

			@Override
			public void onFailure(Call<ResponseRegister> call, Throwable t) {
				registerCallback.onRegisterFailed("Register Failed");
			}
		});
	}

	public interface RegisterCallback{
		void onRegister();
		void onRegisterSuccess();
		void onRegisterFailed(String message);
	}
}
