package com.yuehai.android;

import com.yuehai.android.net.ApiUtil;

import library.base.BaseApplication;

/**
 * Created by zhaoyuehai 2019/3/22
 */
public class MyApplication extends BaseApplication {

    public static String BASE_URL;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void clearUser() {
        ApiUtil.getInstance().cancelAll();
        UserData.getInstance().clearUser();
    }
}
