package com.yuehai.android.net;

import com.yuehai.android.net.request.RegisterUserBean;
import com.yuehai.android.net.response.ResultBean;
import com.yuehai.android.net.response.UserBean;
import com.yuehai.android.net.response.UserForListBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zhaoyuehai 2019/3/29
 */
public interface ApiService {
    /**
     * 登录接口
     *
     * @param userName 用户名/手机号
     * @param password 密码
     * @return Observable
     */
    @FormUrlEncoded
    @Headers(TokenInterceptor.HEADER_NO_TOKEN)
    @POST("api/v1/login")
    Observable<ResultBean<UserBean>> login(@Field("username") String userName, @Field("password") String password);

    /**
     * 注册
     */
    @Headers(TokenInterceptor.HEADER_NO_TOKEN)
    @POST("api/v1/register")
    Observable<ResultBean<Object>> register(@Body RegisterUserBean body);

    /**
     * 刷新token接口（同步请求，在拦截器内执行）
     *
     * @return Call
     */
    @FormUrlEncoded
    @Headers(TokenInterceptor.HEADER_NO_TOKEN)
    @POST("api/v1/token")
    Call<ResultBean<UserBean>> refreshToken(@Field("refreshToken") String refreshToken);

    /**
     * 分页加载用户列表
     */
    @Headers(TokenInterceptor.HEADER_NEED_TOKEN)//可以省略此header
    @GET("api/v1/users")
    Observable<ResultBean<List<UserForListBean>>> getUsers(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    /**
     * 删除用户
     */
    @DELETE("api/v1/user/{id}")
    Observable<ResultBean<Object>> deleteUser(@Path("id") Long id);
}
