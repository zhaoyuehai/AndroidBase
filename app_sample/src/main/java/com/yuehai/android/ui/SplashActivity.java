package com.yuehai.android.ui;

import android.content.Intent;

import com.yuehai.android.R;
import com.yuehai.android.contract.SplashContract;
import com.yuehai.android.presenter.SplashPresenter;

import library.base.BaseMvpActivity;

/**
 * Created by zhaoyuehai 2019/3/22
 */
public class SplashActivity extends BaseMvpActivity<SplashContract.Presenter> implements SplashContract.View {

    @Override
    protected SplashContract.Presenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void goMain() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
