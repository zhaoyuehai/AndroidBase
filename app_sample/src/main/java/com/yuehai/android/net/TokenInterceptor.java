package com.yuehai.android.net;

import com.yuehai.android.UserData;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
//        public static final String UN_NEED_JSON_HEADER = "NEED_JSON_HEADER";
//    public static final String UN_NEED_TOKEN = "UN_NEED_TOKEN";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder();
        builder.addHeader("Accept", "application/json");
        builder.addHeader("Authorization", getToken());
        Request request = builder.method(original.method(), original.body()).build();
        return chain.proceed(request);
    }

    private String getToken() {
        String tokenHeader = UserData.getInstance().getUser().getTokenHeader();
        String token = UserData.getInstance().getUser().getToken();
        return tokenHeader + " " + token;
    }
}
