package io.github.handharbeni.erpas.apis;

import io.github.handharbeni.erpas.apis.responses.WP.DataQris;
import io.github.handharbeni.erpas.apis.responses.WP.DataStatusPayment;
import io.github.handharbeni.erpas.apis.responses.WP.GeneralResponse;
import io.github.handharbeni.erpas.apis.responses.WP.LaporanRealisasi;
import io.github.handharbeni.erpas.apis.responses.WP.ListResponseSkrd;
import io.github.handharbeni.erpas.apis.responses.WP.PaymentStatus;
import io.github.handharbeni.erpas.apis.responses.WP.ResponseLogin;
import io.github.handharbeni.erpas.apis.responses.WP.ResponseRegister;
import io.github.handharbeni.erpas.apis.responses.WP.ResponseWp;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClientInterface {
//    @HTTP(method = "GET", path = "request_wp", hasBody = true)

    @POST("update_password")
    Call<GeneralResponse> changePassword(@Body RequestBody requestBody);

    @POST("request_wp")
    Call<ResponseWp> requestWp(@Body RequestBody requestBody);

    @POST("wp_tutup")
    Call<GeneralResponse> requestTutup(@Body RequestBody requestBody);

    @POST("request_qris")
    Call<DataQris> requestQris(@Body RequestBody requestBody);

    @POST("cek_status")
    Call<PaymentStatus> checkPayment(@Body RequestBody requestBody);

    @POST("lap_skrd")
    Call<ListResponseSkrd> skrdReport();

    @POST("lap_skrd")
    Call<ListResponseSkrd> skrdReport(@Body RequestBody requestBody);

    @POST("lap_realisasi")
    Call<LaporanRealisasi> realisasiReport();

    @POST("login_user")
    Call<ResponseLogin> login(@Body RequestBody requestBody);

    @POST("register_user")
    Call<ResponseRegister> register(@Body RequestBody requestBody);

    @POST("cek_status_qris")
    Call<DataStatusPayment> cekPayment(@Body RequestBody requestBody);
}
