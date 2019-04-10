package com.yuehai.android.presenter;

import android.util.Base64;

import com.yuehai.android.contract.LoginContract;
import com.yuehai.android.net.ApiUtil;
import com.yuehai.android.net.ResultObserver;
import com.yuehai.android.net.response.ResultBean;
import com.yuehai.android.net.response.UserBean;
import com.yuehai.android.util.RxUtil;

import io.reactivex.disposables.Disposable;
import library.base.BasePresenter;

/**
 * Login P
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String userName, String password) {
        ApiUtil.getInstance()
                .getApiService()
                .login(userName, Base64.encodeToString(password.getBytes(), Base64.NO_WRAP))
                .compose(RxUtil.io_main())
                .subscribe(new ResultObserver<ResultBean<UserBean>>(this) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        if (isViewAttached()) {
                            getView().showLoading();
                        }
                    }

                    @Override
                    public void onNext(ResultBean<UserBean> bean) {
                        if (isViewAttached()) {
                            getView().showToast("登录成功");
                            getView().dismissLoading();
                            // TODO: 2019/4/10 保存UserData finish

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isViewAttached()) {
                            getView().showToast("登录失败");
                            getView().dismissLoading();
                        }
                    }

                });

    }
}
