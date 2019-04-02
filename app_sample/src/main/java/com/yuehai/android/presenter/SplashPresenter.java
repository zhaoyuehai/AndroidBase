package com.yuehai.android.presenter;

import com.yuehai.android.Contacts;
import com.yuehai.android.MyApplication;
import com.yuehai.android.contract.SplashContract;
import com.yuehai.android.net.ResultObserver;
import com.yuehai.android.util.NetUtil;
import com.yuehai.android.util.SPUtil;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import library.base.BasePresenter;

/**
 * 闪屏启动页 P
 * Created by zhaoyuehai 2019/3/22
 */
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    public SplashPresenter(SplashContract.View view) {
        super(view);
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        if (isViewAttached()) {
            getView().showInputDialog();
        }
    }

    @Override
    public void checkUrl(String ip) {
        Observable
                .create((ObservableOnSubscribe<Boolean>) emitter -> {
                    if (NetUtil.isConnServer(ip)) {
                        SPUtil.getInstance(Contacts.SP_NAME).put(Contacts.SP_IP_ADDRESS, ip);
                        MyApplication.BASE_URL = ip;
                        emitter.onNext(true);
                    } else {
                        emitter.onNext(false);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResultObserver<Boolean>(this) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        if (isViewAttached()) getView().showLoading();
                    }

                    @Override
                    public void onNext(Boolean o) {
                        if (isViewAttached()) {
                            getView().dismissLoading();
                            if (o) {
                                getView().goMain();
                            } else {
                                getView().showInputDialog();
                                getView().showToast("连接失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isViewAttached()) {
                            getView().dismissLoading();
                            getView().showInputDialog();
                            String tips = "连接失败";
                            if (e instanceof SocketTimeoutException) {
                                tips = "响应超时，地址错误或者服务器未启动";
                            } else if (e instanceof ConnectException) {
                                if (NetUtil.isNetworkConnected()) {
                                    tips = "连接失败！地址有问题";
                                } else {
                                    tips = "连接失败！请检查网络连接";
                                }
                            } else if (e instanceof UnknownHostException || e instanceof MalformedURLException) {
                                tips = "连接失败！地址有问题";
                            }
                            getView().showToast(tips);
                        }
                    }
                });

    }
}
