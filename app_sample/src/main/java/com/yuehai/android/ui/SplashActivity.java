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
    protected boolean exitAnimalEnable() {
        return false;
    }

    @Override
    public void goMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showIpDialog() {
        String ip = SPUtil.getInstance(Contacts.SP_NAME).getString(Contacts.SP_IP_ADDRESS, "");
        InputDialogFragment editNameDialogFragment = InputDialogFragment.newInstance("IP地址", ip, new InputDialogFragment.OnClickListener() {
            @Override
            public void onConfirm(String context) {
                SPUtil.getInstance(Contacts.SP_NAME).put(Contacts.SP_IP_ADDRESS, context);
                MyApplication.BASE_URL = context;
                goMain();
            }

            @Override
            public void onCancel() {
                finish();
            }
        });
        editNameDialogFragment.setCancelable(false);
        editNameDialogFragment.show(getSupportFragmentManager(), "IPDialog");
    }
}
