package io.github.handharbeni.erpas.apis;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import io.github.handharbeni.erpas.utils.Constant;
import io.github.handharbeni.erpas.utils.UtilDb;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static final String BASE_URL = Constant.BASE_URL;
    private static Context context;
    private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static final OkHttpClient builderOkHttp = new OkHttpClient.Builder()
            .addInterceptor(chain -> {
                String token = new UtilDb(context).getString(Constant.TOKEN);
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("authorization", token)
                        .build();
                return chain.proceed(request);
            })
            .addInterceptor(loggingInterceptor)
            .callTimeout(Constant.CALL_TIMEOUT, Constant.TIMEUNIT_TIMEOUT)
            .readTimeout(Constant.READ_TIMEOUT, Constant.TIMEUNIT_TIMEOUT)
            .connectTimeout(Constant.CONNECT_TIMEOUT, Constant.TIMEUNIT_TIMEOUT)
            .writeTimeout(Constant.WRITE_TIMEOUT, Constant.TIMEUNIT_TIMEOUT)
            .build();

    private static final Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(builderOkHttp)
            .addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofit = builder.build();

    private static volatile Object instances;

    public static <T> T getInstance(Context contexts, Class<T> type) {
        context = contexts;
        synchronized (Client.class) {
            instances = retrofit.create(type);
        }
        return (T) Client.instances;
    }
}