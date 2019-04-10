package com.yuehai.android.presenter;

import android.util.Base64;

import com.yuehai.android.util.RxUtil;
import com.yuehai.android.contract.RegisterContract;
import com.yuehai.android.net.ApiUtil;
import com.yuehai.android.net.ResultObserver;
import com.yuehai.android.net.request.RegisterUserBen;
import com.yuehai.android.net.response.ResultBean;

import io.reactivex.disposables.Disposable;
import library.base.BasePresenter;

/**
 * 注册 P
 * Created by zhaoyuehai 2019/3/22
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    public RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public void onRegister(String userName, String password, String phone) {
        ApiUtil.getInstance()
                .getApiService()
                .register(new RegisterUserBen(userName, Base64.encodeToString(password.getBytes(), Base64.NO_WRAP),phone))
                .compose(RxUtil.io_main())
                .subscribe(new ResultObserver<ResultBean<String>>(this, true) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        if (isViewAttached()) getView().showLoading();
                    }

                    @Override
                    public void onNext(ResultBean<String> result) {
                        if (isViewAttached()) {
                            if (result != null && result.getData().equals("1")) {
                                getView().showToast("注册成功");
                                getView().finish();
                            } else {
                                getView().showToast("注册失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().dismissLoading();
                            getView().showToast("注册失败");
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (isViewAttached()) getView().dismissLoading();
                    }
                });
    }

}
