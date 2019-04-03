package com.yuehai.android.presenter;

import com.yuehai.android.contract.ThemeSettingContract;

import library.base.BasePresenter;

/**
 * ThemeSetting P
 */
public class ThemeSettingPresenter extends BasePresenter<ThemeSettingContract.View> implements ThemeSettingContract.Presenter {
    public ThemeSettingPresenter(ThemeSettingContract.View view) {
        super(view);
    }
}
