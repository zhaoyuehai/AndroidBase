package com.yuehai.android.contract;

import library.base.IBasePresenter;
import library.base.IBaseView;

/**
 * 用户注册
 * Created by zhaoyuehai 2019/3/22
 */
public interface RegisterContract {
    interface View extends IBaseView {

        void finish();
    }

    interface Presenter extends IBasePresenter {

        void onRegister(String userName, String password);
    }
}
