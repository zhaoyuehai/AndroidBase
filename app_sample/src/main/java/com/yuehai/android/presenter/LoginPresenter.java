package com.yuehai.android.presenter;

import android.text.TextUtils;
import android.util.Base64;

import com.yuehai.android.Contacts;
import com.yuehai.android.UserData;
import com.yuehai.android.contract.LoginContract;
import com.yuehai.android.net.ApiUtil;
import com.yuehai.android.net.ResultObserver;
import com.yuehai.android.net.response.ResultBean;
import com.yuehai.android.net.response.UserBean;
import com.yuehai.android.util.RxUtil;
import com.yuehai.android.util.SPUtil;

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
    protected void onCreate() {
        super.onCreate();
        String username = SPUtil.getInstance(Contacts.SP_NAME).getString(Contacts.LOGIN_NAME);
        if (isViewAttached() && !TextUtils.isEmpty(username)) {
            getView().initUserName(username);
        }
    }

    @Override
    public void login(String userName, String password) {
        SPUtil.getInstance(Contacts.SP_NAME).put(Contacts.LOGIN_NAME, userName);
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
                            getView().dismissLoading();
                            getView().showToast(bean.getMessage());
                            if (bean.isSuccess()) {
                                UserData.getInstance().saveUser(bean.getData());
                                getView().onLoginSuccess();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isViewAttached()) {
                            getView().dismissLoading();
                            getView().showToast(e.getMessage());
                        }
                    }

                });
    }
}
