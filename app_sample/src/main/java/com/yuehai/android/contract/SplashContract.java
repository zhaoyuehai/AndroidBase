package com.yuehai.android.contract;

import library.base.IBasePresenter;
import library.base.IBaseView;

/**
 * 闪屏启动页
 * Created by zhaoyuehai 2019/3/22
 */
public interface SplashContract {
    interface View extends IBaseView {
        /**
         * 去主界面
         */
        void goMain();

        /**
         * 输入服务器地址弹框
         */
        void showInputDialog();

    }

    interface Presenter extends IBasePresenter {

        /**
         * 检查服务器地址是否连通
         *
         * @param url 服务器地址
         */
        void checkUrl(String url);
    }
}
