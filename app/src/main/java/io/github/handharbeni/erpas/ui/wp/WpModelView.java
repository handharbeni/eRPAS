package io.github.handharbeni.erpas.ui.wp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import org.json.JSONObject;

import java.util.HashMap;

import io.github.handharbeni.erpas.apis.Client;
import io.github.handharbeni.erpas.apis.ClientInterface;
import io.github.handharbeni.erpas.apis.responses.WP.DataQris;
import io.github.handharbeni.erpas.apis.responses.WP.DataStatusPayment;
import io.github.handharbeni.erpas.apis.responses.WP.GeneralResponse;
import io.github.handharbeni.erpas.apis.responses.WP.LaporanRealisasi;
import io.github.handharbeni.erpas.apis.responses.WP.ListResponseSkrd;
import io.github.handharbeni.erpas.apis.responses.WP.PaymentStatus;
import io.github.handharbeni.erpas.apis.responses.WP.ResponseWp;
import io.github.handharbeni.erpas.cores.BaseModelView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WpModelView extends BaseModelView {
	Context context;
	ClientInterface client;

	WpCallback wpCallback;

	public WpModelView() {

	}

	public void setupClient(Context context, WpCallback wpCallback) {
		this.context = context;
		client = Client.getInstance(this.context, ClientInterface.class);
		this.wpCallback = wpCallback;
	}

	public void changePassword(String passwordLama, String passwordBaru) {
		wpCallback.onLoad();

		HashMap<String, String> dataPassword = new HashMap<>();
		dataPassword.put("_id_user", getDb(this.context).getString("IdUser"));
		dataPassword.put("password_lama", passwordLama);
		dataPassword.put("password_baru", passwordBaru);

		JSONObject jsonObject = new JSONObject(dataPassword);

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);


		client.changePassword(body).enqueue(new Callback<GeneralResponse>() {
			@Override
			public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
				if (response.isSuccessful()) {
					if (response.body() != null) {
						if (response.body().getStatus().equalsIgnoreCase("Sukses")) {
							wpCallback.onSuccessChangePassword();
						} else {
							wpCallback.onFailed(response.body().getExpose());
						}
					} else {
						wpCallback.onFailed("Something went wrong for update your password");
					}
				} else {
					wpCallback.onFailed("Something went wrong");
				}
			}

			@Override
			public void onFailure(Call<GeneralResponse> call, Throwable t) {
				wpCallback.onFailed(t.getMessage());
			}
		});
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
			public void onResponse(@NonNull Call<ResponseWp> call, @NonNull Response<ResponseWp> response) {
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
			public void onFailure(@NonNull Call<ResponseWp> call, @NonNull Throwable t) {
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
			public void onResponse(@NonNull Call<GeneralResponse> call, @NonNull Response<GeneralResponse> response) {
				if (response.isSuccessful()) {
					if (response.body().getStatus().equalsIgnoreCase("sukses")) {
						wpCallback.onSuccessTutup();
					}
				}
				wpCallback.onFailed("Gagal mendapat data");
			}

			@Override
			public void onFailure(@NonNull Call<GeneralResponse> call, @NonNull Throwable t) {
				wpCallback.onFailed(t.getMessage());
			}
		});

	}

	public void fetchQris(String npwrd, String amount) {
		wpCallback.onLoad();

		HashMap<String, String> dataNpwrd = new HashMap<>();
		dataNpwrd.put("npwrd", npwrd);
		dataNpwrd.put("amount", amount);
		dataNpwrd.put("_id_user", getDb(this.context).getString("IdUser"));

		JSONObject jsonObject = new JSONObject(dataNpwrd);

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
		client.requestQris(body).enqueue(new Callback<DataQris>() {
			@Override
			public void onResponse(@NonNull Call<DataQris> call, @NonNull Response<DataQris> response) {
				if (response.isSuccessful()) {
					if (response.body().getStatus().equalsIgnoreCase("sukses")) {
						wpCallback.onSuccessQris(response.body().getQris());
					}
				}

				wpCallback.onFailed("Gagal mendapat data");
			}

			@Override
			public void onFailure(@NonNull Call<DataQris> call, @NonNull Throwable t) {
				wpCallback.onFailed(t.getMessage());
			}
		});
	}

	public void checkPayment(String npwrd, String amount) {
		wpCallback.onLoad();

		HashMap<String, String> dataNpwrd = new HashMap<>();
		dataNpwrd.put("npwrd", npwrd);
		dataNpwrd.put("amount", amount);
		dataNpwrd.put("_id_user", getDb(this.context).getString("IdUser"));

		JSONObject jsonObject = new JSONObject(dataNpwrd);

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);

		client.checkPayment(body).enqueue(new Callback<PaymentStatus>() {
			@Override
			public void onResponse(@NonNull Call<PaymentStatus> call, @NonNull Response<PaymentStatus> response) {
				if (response.isSuccessful()) {
					if (response.body().getStatus().equalsIgnoreCase("sukses")) {
						wpCallback.onPaymentSuccess(response.body());
					} else {
						wpCallback.onFailed("Gagal mendapat data");
					}
				} else {
					wpCallback.onFailed("Gagal mendapat data");
				}
			}

			@Override
			public void onFailure(@NonNull Call<PaymentStatus> call, @NonNull Throwable t) {
				wpCallback.onFailed(t.getMessage());
			}
		});
	}

	public void skrdReport() {
		wpCallback.onLoad();
		client.skrdReport().enqueue(new Callback<ListResponseSkrd>() {
			@Override
			public void onResponse(
					@NonNull Call<ListResponseSkrd> call, @NonNull Response<ListResponseSkrd> response
			) {
				if (response.isSuccessful()) {
					if (response.body() != null) {
						if (response.body().getStatus().equalsIgnoreCase("sukses")) {
							if (response.body().getDataSkrd().size() > 0) {
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
				} else {
					wpCallback.onFailed("Gagal mendapat data");
				}
			}

			@Override
			public void onFailure(@NonNull Call<ListResponseSkrd> call, @NonNull Throwable t) {
				wpCallback.onFailed(t.getMessage());
			}
		});
	}

	public void skrdReport(String key, String value) {
		wpCallback.onLoad();

		HashMap<String, String> dataNpwrd = new HashMap<>();
		dataNpwrd.put(key, value);

		JSONObject jsonObject = new JSONObject(dataNpwrd);

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);

		client.skrdReport(body).enqueue(new Callback<ListResponseSkrd>() {
			@Override
			public void onResponse(
					@NonNull Call<ListResponseSkrd> call, @NonNull Response<ListResponseSkrd> response
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
			public void onFailure(@NonNull Call<ListResponseSkrd> call, @NonNull Throwable t) {
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
					@NonNull Call<ListResponseSkrd> call, @NonNull Response<ListResponseSkrd> response
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
			public void onFailure(@NonNull Call<ListResponseSkrd> call, @NonNull Throwable t) {
				wpCallback.onFailed(t.getMessage());
			}
		});
	}

	public void realisasiReport() {
		wpCallback.onLoad();
		client.realisasiReport().enqueue(new Callback<LaporanRealisasi>() {
			@Override
			public void onResponse(
					@NonNull Call<LaporanRealisasi> call, @NonNull Response<LaporanRealisasi> response
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
			public void onFailure(@NonNull Call<LaporanRealisasi> call, @NonNull Throwable t) {
				wpCallback.onFailed(t.getMessage());
			}
		});
	}

	public void checkPaymentStatus(PaymentStatus paymentStatus) {
		wpCallback.onLoad();

		HashMap<String, String> dataQris = new HashMap<>();
		dataQris.put("qris_id", paymentStatus.getInvoiceId());

		JSONObject jsonObject = new JSONObject(dataQris);

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
		client.cekPayment(body).enqueue(new Callback<DataStatusPayment>() {
			@Override
			public void onResponse(
					Call<DataStatusPayment> call, Response<DataStatusPayment> response
			) {
				if (response.isSuccessful()) {
					if (response.body() != null) {
						if (response.body().getStatus().equalsIgnoreCase("sukses")) {
							boolean isPaid = response.body().getData().getIsPaid();
							String recordStatus = response.body().getData().getRecordStatus();
							String transactionStatus = response.body().getData().getTransactionStatus();

							if (recordStatus.equalsIgnoreCase("live") && isPaid) {
								// qris terbayar
								DataStatusPayment.DataPayment dataPayment = response.body().getData();
								paymentStatus.setStatus("Sukses");
								paymentStatus.setKodeBilling(dataPayment.getMerchantMpan());
								paymentStatus.setAmount(dataPayment.getTransactionAmount());
								paymentStatus.setStatusBayar("1");
								paymentStatus.setTransactionDate(dataPayment.getTransactionDate());
								paymentStatus.setInvoiceId(dataPayment.getInvoiceNumber());
								wpCallback.onPaymentSuccess(paymentStatus);
							} else if (recordStatus.equalsIgnoreCase("reversed") && isPaid) {
								// qris dikembalikan
								wpCallback.onFailed("Pembayaran Gagal, Dana dikembalikan");
							} else if (recordStatus.equalsIgnoreCase("confirming") && !isPaid) {
								// qris belum terbayar
								wpCallback.onFailed("QRIS belum terbayar");
							}
						}
					}
				}
			}

			@Override
			public void onFailure(Call<DataStatusPayment> call, Throwable t) {
				wpCallback.onFailed(t.getMessage());
			}
		});
	}


	public interface WpCallback{
		void onLoad();
		void onSuccess(ResponseWp responseWp);
		void onPaymentSuccess(PaymentStatus paymentStatus);
		void onSuccessTutup();
		void onSuccessChangePassword();
		void onSkrdSuccess(ListResponseSkrd listResponseSkrd);
		void onRealisasiSuccess(LaporanRealisasi laporanRealisasi);
		void onSuccessQris(String qris);
		void onFailed(String message);
	}

}
