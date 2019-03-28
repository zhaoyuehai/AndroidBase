package com.yuehai.android.contract;

import com.yuehai.android.vo.UserListBean;

import androidx.annotation.Nullable;
import library.base.IBasePresenter;
import library.base.IBaseView;

/**
 * Created by zhaoyuehai 2019/3/22
 */
public interface UserListContract {
    interface View extends IBaseView {

        void showData(@Nullable UserListBean result);
    }

    interface Presenter extends IBasePresenter {

    }
}
