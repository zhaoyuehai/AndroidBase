package com.yuehai.android.contract;

import library.base.IBasePresenter;
import library.base.IBaseView;

/**
 * 用户注册
 * Created by zhaoyuehai 2019/3/22
 */
public interface RegisterContract {
    interface View extends IBaseView {
        /**
         * 注册成功
         */
        void onRegisterSuccess(String username);
    }

    interface Presenter extends IBasePresenter {
        /**
         * 注册
         *
         * @param userName 用户名
         * @param password 密码
         * @param phone    手机号
         */
        void onRegister(String userName, String password, String phone);
    }
}
