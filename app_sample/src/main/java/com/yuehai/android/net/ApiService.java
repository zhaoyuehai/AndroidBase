package com.yuehai.android.net;

import com.yuehai.android.net.request.RegisterUserBen;
import com.yuehai.android.vo.ResultBean;
import com.yuehai.android.vo.UserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zhaoyuehai 2019/3/29
 */
public interface ApiService {
    /**
     * 分页加载用户列表
     */
    @GET("users")
    Observable<ResultBean<List<UserBean>>> getUsers(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    /**
     * 注册新用户
     */
    @POST("user")
    Observable<ResultBean<String>> register(@Body RegisterUserBen body);

    /**
     * 删除用户
     */
    @DELETE("user/{id}")
    Observable<ResultBean<String>> deleteUser(@Path("id") Long id);
}
