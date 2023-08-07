package io.github.handharbeni.erpas.ui.wp;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import org.json.JSONObject;

import java.util.HashMap;

import io.github.handharbeni.erpas.apis.Client;
import io.github.handharbeni.erpas.apis.ClientInterface;
import io.github.handharbeni.erpas.apis.responses.WP.DataQris;
import io.github.handharbeni.erpas.apis.responses.WP.GeneralResponse;
import io.github.handharbeni.erpas.apis.responses.WP.LaporanRealisasi;
import io.github.handharbeni.erpas.apis.responses.WP.ListResponseSkrd;
import io.github.handharbeni.erpas.apis.responses.WP.PaymentStatus;
import io.github.handharbeni.erpas.apis.responses.WP.ResponseWp;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WpModelView extends ViewModel {
	ClientInterface client;

	WpCallback wpCallback;

	public WpModelView() {

	}

	public void setupClient(Context context, WpCallback wpCallback) {
		client = Client.getInstance(context, ClientInterface.class);
		this.wpCallback = wpCallback;
	}

	public void fetchWp(String npwrd) {
		wpCallback.onLoad();

		HashMap<String, String> dataNpwrd = new HashMap<>();
		dataNpwrd.put("npwrd", npwrd);

		JSONObject jsonObject = new JSONObject(dataNpwrd);

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
		client.requestWp(body).enqueue(new Callback<ResponseWp>() {
			@Override
			public void onResponse(Call<ResponseWp> call, Response<ResponseWp> response) {
				if (response.isSuccessful()) {
					if (response.body() != null) {
						if (response.body().getStatus().equalsIgnoreCase("Sukses")) {
							wpCallback.onSuccess(response.body());
						} else {
							wpCallback.onFailed(response.body().getData());
						}
					} else {
						wpCallback.onFailed("Something went wrong");
					}
				} else {
					wpCallback.onFailed("Something went wrong");
				}
			}

			@Override
			public void onFailure(Call<ResponseWp> call, Throwable t) {
				wpCallback.onFailed(t.getMessage());
			}
		});
	}

	public void kiosTutup(String npwrd, String amount) {
		wpCallback.onLoad();

		HashMap<String, String> dataNpwrd = new HashMap<>();
		dataNpwrd.put("npwrd", npwrd);
		dataNpwrd.put("amount", amount);

		JSONObject jsonObject = new JSONObject(dataNpwrd);

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
		client.requestTutup(body).enqueue(new Callback<GeneralResponse>() {
			@Override
			public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
				if (response.isSuccessful()) {
					if (response.body().getStatus().equalsIgnoreCase("sukses")) {
						wpCallback.onSuccessTutup();
					}
				}
				wpCallback.onFailed("Gagal mendapat data");
			}

			@Override
			public void onFailure(Call<GeneralResponse> call, Throwable t) {
				wpCallback.onFailed(t.getMessage());
			}
		});

	}

	public void fetchQris(String npwrd, String amount) {
		wpCallback.onLoad();

		HashMap<String, String> dataNpwrd = new HashMap<>();
		dataNpwrd.put("npwrd", npwrd);
		dataNpwrd.put("amount", amount);

		JSONObject jsonObject = new JSONObject(dataNpwrd);

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
		client.requestQris(body).enqueue(new Callback<DataQris>() {
			@Override
			public void onResponse(Call<DataQris> call, Response<DataQris> response) {
				if (response.isSuccessful()) {
					if (response.body().getStatus().equalsIgnoreCase("sukses")) {
						wpCallback.onSuccessQris(response.body().getQris());
					}
				}

				wpCallback.onFailed("Gagal mendapat data");
			}

			@Override
			public void onFailure(Call<DataQris> call, Throwable t) {
				wpCallback.onFailed(t.getMessage());
			}
		});
	}

	public void checkPayment(String npwrd, String amount) {
		wpCallback.onLoad();

		HashMap<String, String> dataNpwrd = new HashMap<>();
		dataNpwrd.put("npwrd", npwrd);
		dataNpwrd.put("amount", amount);

		JSONObject jsonObject = new JSONObject(dataNpwrd);

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);

		client.checkPayment(body).enqueue(new Callback<PaymentStatus>() {
			@Override
			public void onResponse(Call<PaymentStatus> call, Response<PaymentStatus> response) {
				if (response.isSuccessful()) {
					if (response.body().getStatus().equalsIgnoreCase("sukses")) {
						wpCallback.onPaymentSuccess(response.body());
					}
				}
				wpCallback.onFailed("Gagal mendapat data");
			}

			@Override
			public void onFailure(Call<PaymentStatus> call, Throwable t) {
				wpCallback.onFailed(t.getMessage());
			}
		});
	}

	public void skrdReport(String npwrd) {
		wpCallback.onLoad();

		HashMap<String, String> dataNpwrd = new HashMap<>();
		dataNpwrd.put("npwrd", npwrd);

		JSONObject jsonObject = new JSONObject(dataNpwrd);

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);

		client.skrdReport(body).enqueue(new Callback<ListResponseSkrd>() {
			@Override
			public void onResponse(
					Call<ListResponseSkrd> call, Response<ListResponseSkrd> response
			) {
				if (response.isSuccessful()) {
					if (response.body() != null) {
						if (response.body().getStatus().equalsIgnoreCase("sukses")) {
							wpCallback.onSkrdSuccess(response.body());
						} else {
							wpCallback.onFailed("Gagal mendapat data");
						}
					} else {
						wpCallback.onFailed("Gagal mendapat data");
					}
				} else {
					wpCallback.onFailed("Gagal mendapat data");
				}
			}

			@Override
			public void onFailure(Call<ListResponseSkrd> call, Throwable t) {
				wpCallback.onFailed(t.getMessage());
			}
		});
	}

	public void realisasiReport() {
		wpCallback.onLoad();
		client.realisasiReport().enqueue(new Callback<LaporanRealisasi>() {
			@Override
			public void onResponse(
					Call<LaporanRealisasi> call, Response<LaporanRealisasi> response
			) {
				if (response.isSuccessful()) {
					if (response.body() != null) {
						if (response.body().getStatus().equalsIgnoreCase("sukses")) {
							wpCallback.onRealisasiSuccess(response.body());
						} else {
							wpCallback.onFailed("Gagal mendapatkan data");
						}
					} else {
						wpCallback.onFailed("Gagal mendapatkan data");
					}
				} else {
					wpCallback.onFailed("Gagal mendapatkan data");
				}
			}

			@Override
			public void onFailure(Call<LaporanRealisasi> call, Throwable t) {
				wpCallback.onFailed(t.getMessage());
			}
		});
	}


	public interface WpCallback{
		void onLoad();
		void onSuccess(ResponseWp responseWp);
		void onPaymentSuccess(PaymentStatus paymentStatus);
		void onSuccessTutup();
		void onSkrdSuccess(ListResponseSkrd listResponseSkrd);
		void onRealisasiSuccess(LaporanRealisasi laporanRealisasi);
		void onSuccessQris(String qris);
		void onFailed(String message);
	}

}
