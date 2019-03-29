package com.yuehai.android.contract;

import com.yuehai.android.vo.ResultBean;
import com.yuehai.android.vo.UserBean;

import java.util.List;

import androidx.annotation.Nullable;
import library.base.IBasePresenter;
import library.base.IBaseView;

/**
 * Created by zhaoyuehai 2019/3/22
 */
public interface UserListContract {
    interface View extends IBaseView {

        void showData(@Nullable ResultBean<List<UserBean>> result);
    }

    interface Presenter extends IBasePresenter {

    }
}
