package com.yuehai.android.ui;

import android.os.Environment;
import android.widget.Switch;

import com.yuehai.android.R;
import com.yuehai.android.contract.ThemeSettingContract;
import com.yuehai.android.presenter.ThemeSettingPresenter;
import com.zhy.changeskin.SkinManager;

import java.io.File;

import butterknife.BindView;
import library.base.BaseMvpActivity;

/**
 * ThemeSetting V
 */
public class ThemeSettingActivity extends BaseMvpActivity<ThemeSettingContract.Presenter> implements ThemeSettingContract.View {

    @BindView(R.id.theme_switch)
    Switch mSwitch;

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

    private String mSkinPkgPath = Environment.getExternalStorageDirectory() + File.separator + "skin_plugin.apk";

    @Override
    protected void initView() {
        super.initView();
        mSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            SkinManager.getInstance().changeSkin(mSkinPkgPath, "com.imooc.skin_plugin", new com.zhy.changeskin.callback.ISkinChangingCallback() {
                @Override
                public void onStart() {
                }

                @Override
                public void onError(Exception e) {
                }

                @Override
                public void onComplete() {
                }
            });
        });
    }
}
