package com.yuehai.android.net;

import android.util.Log;

import com.yuehai.android.MyApplication;
import com.yuehai.android.util.LogUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhaoyuehai 2019/3/29
 */
public class ApiUtil {

    private ApiUtil() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(message -> {
            try {
                String text = URLDecoder.decode(message, "utf-8");
                LogUtil.i("ApiUtil---", text);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.i("ApiUtil---", message);
            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(logInterceptor)
                .addInterceptor(new TokenInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(MyApplication.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static ApiUtil getInstance() {
        return ClassHolder.INSTANCE;
    }

    private static class ClassHolder {
        private static final ApiUtil INSTANCE = new ApiUtil();
    }

    private final OkHttpClient okHttpClient;
    private ApiService apiService;

    public ApiService getApiService() {
        return this.apiService;
    }

    /**
     * 取消请求
     */
    public void cancelAll() {
        if (okHttpClient != null) {
            okHttpClient.dispatcher().cancelAll();
        }
    }
}
