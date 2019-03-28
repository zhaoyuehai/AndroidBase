package com.yuehai.android.contract;

import library.base.IBasePresenter;
import library.base.IBaseView;

/**
 * Created by zhaoyuehai 2019/3/22
 */
public interface SplashContract {
    interface View extends IBaseView {
        void goMain();
    }

    interface Presenter extends IBasePresenter {

    }
}
