package com.yuehai.android.contract;

import com.yuehai.android.vo.UserBean;

import library.base.IBasePresenter;
import library.base.IBaseView;

/**
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
