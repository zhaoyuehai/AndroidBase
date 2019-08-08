package com.yuehai.android.presenter;

import android.util.Base64;

import androidx.annotation.Nullable;

import com.yuehai.android.contract.RegisterContract;
import com.yuehai.android.net.ApiUtil;
import com.yuehai.android.net.ResultObserver;
import com.yuehai.android.net.request.RegisterUserBean;
import com.yuehai.android.net.response.ResultBean;
import com.yuehai.android.net.response.UserForListBean;
import com.yuehai.android.util.RxUtil;

import io.reactivex.disposables.Disposable;
import library.base.BasePresenter;

/**
 * 注册/修改 P
 * Created by zhaoyuehai 2019/3/22
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    public RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public void onRegister(String userName, String password, String phone) {//password->Base64进行编码
        ApiUtil.getInstance()
                .getApiService()
                .register(new RegisterUserBean(userName, Base64.encodeToString(password.getBytes(), Base64.NO_WRAP), phone))
                .compose(RxUtil.io_main())
                .subscribe(new ResultObserver<ResultBean<Long>>(this, true) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        if (isViewAttached()) getView().showLoading();
                    }

                    @Override
                    public void onNext(ResultBean<Long> result) {
                        if (isViewAttached()) {
                            getView().showToast(result.getMessage());
                            getView().dismissLoading();
                            if (result.isSuccess()) {
                                getView().onRegisterSuccess(userName,result.getData());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().dismissLoading();
                            getView().showToast(e.getMessage());
                        }
                    }
                });
    }

    @Override
    public void onModifyUser(long id, String phone, @Nullable String email, @Nullable String nickName) {
        ApiUtil.getInstance()
                .getApiService()
                .updateUser(new UserForListBean(id, phone, email, nickName))
                .compose(RxUtil.io_main())
                .subscribe(new ResultObserver<ResultBean<Object>>(this, true) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        if (isViewAttached()) getView().showLoading();
                    }

                    @Override
                    public void onNext(ResultBean<Object> result) {
                        if (isViewAttached()) {
                            getView().showToast(result.getMessage());
                            getView().dismissLoading();
                            if (result.isSuccess()) {
                                getView().onModifySuccess();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().dismissLoading();
                            getView().showToast(e.getMessage());
                        }
                    }
                });
    }
}
