package com.yuehai.android.presenter;

import com.yuehai.android.contract.SetPwdContract;

import library.base.BasePresenter;

/**
 * SetPwd P
 */
public class SetPwdPresenter extends BasePresenter<SetPwdContract.View> implements SetPwdContract.Presenter {
    public SetPwdPresenter(SetPwdContract.View view) {
        super(view);
    }
}
