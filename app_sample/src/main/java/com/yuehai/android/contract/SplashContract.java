package com.yuehai.android.contract;

import library.base.IBasePresenter;
import library.base.IBaseView;

/**
 * Created by zhaoyuehai 2019/3/22
 */
public interface SplashContract {
    interface View extends IBaseView {
        void goMain();

        /**
         * 输入IP地址
         */
        void showIpDialog();

    }

    interface Presenter extends IBasePresenter {

    }
}
