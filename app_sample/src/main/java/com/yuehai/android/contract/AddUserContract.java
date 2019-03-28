package com.yuehai.android.contract;

import com.yuehai.android.vo.UserBean;

import library.base.IBasePresenter;
import library.base.IBaseView;

/**
 * Created by zhaoyuehai 2019/3/22
 */
public interface AddUserContract {
    interface View extends IBaseView {

    }

    interface Presenter extends IBasePresenter {
        void onAddUser(UserBean userBean);
    }
}
