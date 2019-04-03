package com.yuehai.android.ui;

import com.yuehai.android.R;
import com.yuehai.android.contract.ThemeSettingContract;
import com.yuehai.android.presenter.ThemeSettingPresenter;

import library.base.BaseMvpActivity;

/**
 * ThemeSetting V
 */
public class ThemeSettingActivity extends BaseMvpActivity<ThemeSettingContract.Presenter> implements ThemeSettingContract.View {


    @Override
    protected int getInnerViewId() {
        return R.layout.activity_theme_setting;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.theme_setting;
    }

    @Override
    protected ThemeSettingContract.Presenter createPresenter() {
        return new ThemeSettingPresenter(this);
    }

}
