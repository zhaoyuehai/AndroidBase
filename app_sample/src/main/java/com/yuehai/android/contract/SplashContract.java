package com.yuehai.android.contract;

import com.yuehai.android.widget.InputDialogFragment;

import library.base.BaseActivity;
import library.base.IBasePresenter;
import library.base.IBaseView;

/**
 * 闪屏启动页
 * Created by zhaoyuehai 2019/3/22
 */
public interface SplashContract {
    interface View extends IBaseView {
        /**
         * 输入服务器地址弹框
         */
        void showInputDialog();

        /**
         * 去主界面/登录页
         */
        void goNextPage(Class<? extends BaseActivity> activity);
    }

    interface Presenter extends IBasePresenter , InputDialogFragment.OnClickListener {
    }
}
