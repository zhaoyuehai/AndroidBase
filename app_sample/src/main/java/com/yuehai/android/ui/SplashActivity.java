package com.yuehai.android.ui;

import android.content.Intent;

import com.yuehai.android.Contacts;
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
public class SplashActivity extends BaseMvpActivity<SplashContract.Presenter> implements SplashContract.View, InputDialogFragment.OnClickListener {

    private InputDialogFragment inputDialogFragment;

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
    public void showInputDialog() {
        String FGTag = "InputDialogFragment";
        if (inputDialogFragment == null) {
            inputDialogFragment = (InputDialogFragment) getSupportFragmentManager().findFragmentByTag(FGTag);
            if (inputDialogFragment == null) {
                String ip = SPUtil.getInstance(Contacts.SP_NAME).getString(Contacts.SP_IP_ADDRESS, "");
                inputDialogFragment = InputDialogFragment.newInstance("请输入服务器地址", ip);
                inputDialogFragment.setCancelable(false);
            }
            inputDialogFragment.setOnConfirmListener(this);
        }
        if (!inputDialogFragment.isAdded())
            inputDialogFragment.show(getSupportFragmentManager(), FGTag);
    }

    @Override
    public void onConfirm(String content) {
        presenter.checkUrl(content);
    }

    @Override
    public void onCancel() {
        finish();
    }
}
