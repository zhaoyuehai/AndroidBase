package com.yuehai.android.ui;

import android.content.Intent;

import com.yuehai.android.BuildConfig;
import com.yuehai.android.Contacts;
import com.yuehai.android.MyApplication;
import com.yuehai.android.R;
import com.yuehai.android.contract.SplashContract;
import com.yuehai.android.presenter.SplashPresenter;
import com.yuehai.android.util.SPUtil;
import com.yuehai.android.widget.InputDialogFragment;

import library.base.BaseMvpActivity;

/**
 * 闪屏启动页 V
 * Created by zhaoyuehai 2019/3/22
 */
public class SplashActivity extends BaseMvpActivity<SplashContract.Presenter> implements SplashContract.View{

    @Override
    protected SplashContract.Presenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected boolean exitAnimalEnable() {
        return false;
    }

    @Override
    public void goMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
