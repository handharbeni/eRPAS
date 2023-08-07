package io.github.handharbeni.erpas.apis;

import io.github.handharbeni.erpas.apis.responses.DataResponse;
import io.github.handharbeni.erpas.apis.responses.ListResponse;
import io.github.handharbeni.erpas.apis.responses.WP.DataQris;
import io.github.handharbeni.erpas.apis.responses.WP.GeneralResponse;
import io.github.handharbeni.erpas.apis.responses.WP.LaporanRealisasi;
import io.github.handharbeni.erpas.apis.responses.WP.ListResponseSkrd;
import io.github.handharbeni.erpas.apis.responses.WP.PaymentStatus;
import io.github.handharbeni.erpas.apis.responses.WP.ResponseWp;
import io.github.handharbeni.erpas.apis.responses.data.DataBphtb;
import io.github.handharbeni.erpas.apis.responses.data.DataDendaPayment;
import io.github.handharbeni.erpas.apis.responses.data.DataLogin;
import io.github.handharbeni.erpas.apis.responses.data.DataObjekPbb;
import io.github.handharbeni.erpas.apis.responses.data.DataPbbBayar;
import io.github.handharbeni.erpas.apis.responses.data.DataStatusPayment;
import io.github.handharbeni.erpas.utils.Constant;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface ClientInterface {
//    @HTTP(method = "GET", path = "request_wp", hasBody = true)
    @POST("request_wp")
    Call<ResponseWp> requestWp(@Body RequestBody requestBody);

    @POST("wp_tutup")
    Call<GeneralResponse> requestTutup(@Body RequestBody requestBody);

    @POST("request_qris")
    Call<DataQris> requestQris(@Body RequestBody requestBody);

    @POST("cek_status")
    Call<PaymentStatus> checkPayment(@Body RequestBody requestBody);

    @POST("lap_skrd")
    Call<ListResponseSkrd> skrdReport(@Body RequestBody requestBody);

    @POST("lap_realisasi")
    Call<LaporanRealisasi> realisasiReport();
}
