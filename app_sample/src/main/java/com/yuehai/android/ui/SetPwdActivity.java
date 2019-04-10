package com.yuehai.android.ui;

import com.yuehai.android.R;
import com.yuehai.android.contract.SetPwdContract;
import com.yuehai.android.presenter.SetPwdPresenter;

import library.base.BaseMvpActivity;

/**
 * SetPwd V
 */
public class SetPwdActivity extends BaseMvpActivity<SetPwdContract.Presenter> implements SetPwdContract.View {

    @Override
    protected int getToolbarTitle() {
        return R.string.reset_pwd;
    }

    @Override
    protected int getInnerViewId() {
        return R.layout.activity_set_password;
    }

    @Override
    protected SetPwdContract.Presenter createPresenter() {
        return new SetPwdPresenter(this);
    }

}
