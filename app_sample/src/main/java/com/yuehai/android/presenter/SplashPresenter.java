package com.yuehai.android.presenter;

import com.yuehai.android.contract.SplashContract;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
        add(Observable
                .timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (isViewAttached()) {
                        getView().goMain();
                    }
                }));
    }
}
