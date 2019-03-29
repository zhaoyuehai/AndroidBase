package com.yuehai.android.net;

import com.yuehai.android.net.request.RegisterUserBen;
import com.yuehai.android.vo.ResultBean;
import com.yuehai.android.vo.UserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zhaoyuehai 2019/3/29
 */
public interface ApiService {
    @GET("users")
    Observable<ResultBean<List<UserBean>>> users(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);
    @POST("user")
    Observable<ResultBean<String>> register(@Body RegisterUserBen body);
}
