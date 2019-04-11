package com.yuehai.android.net;

import android.app.Activity;
import android.content.Intent;

import com.yuehai.android.MyApplication;
import com.yuehai.android.UserData;
import com.yuehai.android.net.response.ResultBean;
import com.yuehai.android.net.response.UserBean;
import com.yuehai.android.ui.LoginActivity;
import com.yuehai.android.ui.RegisterActivity;
import com.yuehai.android.ui.SetPwdActivity;
import com.yuehai.android.util.LogUtil;

import java.io.IOException;
import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;

public class TokenInterceptor implements Interceptor {
    static final String HEADER_NO_TOKEN = "NeedToken: false";
    static final String HEADER_NEED_TOKEN = "NeedToken: true";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder();
        builder.addHeader("Accept", "application/json");
        //部分接口需要Token
        String needToken = original.header("NeedToken");
        if (needToken == null || needToken.equals("true")) {//NeedToken为null时默认添加token
            String token = getToken();
            if (token != null) {
                builder.header("Authorization", token);
            } else {
                clearAndLogin();
                throw new ApiException(401, "需要重新登录");
            }
        }
        Request request = builder
                .removeHeader("NeedToken")
                .method(original.method(), original.body())
                .build();
        Response response = chain.proceed(request);
        if (response.code() == 401) {//token失效
            clearAndLogin();
            throw new ApiException(401, "需要重新登录");
        } else if (response.code() == 403) {
            throw new ApiException(403, "没有权限");
        }
        return response;
    }

    private synchronized String getToken() {
        String token = null;
        if (UserData.getInstance().getUser() == null) {
            return null;
        }
        long expiration = UserData.getInstance().getUser().getExpiration() - 10 * 60 * 1000;//提前10分钟（后端正式配置8小时过期，提前10分钟刷新token即可）
//        long expiration = UserData.getInstance().getUser().getExpiration() - 180 * 1000;//测试状态：提前3分钟(后端测试配置5分钟过期，再提前3分钟，即2分钟后就过期)
        LogUtil.i("======过期时间是：" + new Date(expiration).toString());
        LogUtil.i("======当前时间是：" + new Date(System.currentTimeMillis()).toString());
        if (new Date(expiration).before(new Date())) { //本地判断token过期
            LogUtil.e("已过期，需要去刷新token");
            Call<ResultBean<UserBean>> call = ApiUtil.getInstance()
                    .getApiService()
                    .refreshToken(UserData.getInstance().getUser().getRefreshToken());
            ResultBean<UserBean> resultBean = null;
            try {
                resultBean = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (resultBean != null && resultBean.getCode().equals("10000") && resultBean.getData() != null) {
                    LogUtil.e("======刷新token成功");
                    UserData.getInstance().saveUser(resultBean.getData());
                    token = UserData.getInstance().getUser().getTokenHeader() + UserData.getInstance().getUser().getToken();
                }
            }
        } else {
            LogUtil.i("======token未过期");
            token = UserData.getInstance().getUser().getTokenHeader() + UserData.getInstance().getUser().getToken();
        }
        return token;
    }

    private synchronized void clearAndLogin() {
        if (isLoginPage()) return;
        MyApplication.clearUser();
        MyApplication.getInstance().startActivity(new Intent(MyApplication.getInstance(), LoginActivity.class));
    }

    private boolean isLoginPage() {
        Activity topActivity = MyApplication.getInstance().getTopActivity();
        if (topActivity == null) return false;
        return topActivity.getLocalClassName().endsWith(LoginActivity.class.getSimpleName())
                || topActivity.getLocalClassName().endsWith(RegisterActivity.class.getSimpleName())
                || topActivity.getLocalClassName().endsWith(SetPwdActivity.class.getSimpleName());
    }
}
