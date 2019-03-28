package com.yuehai.android.ui;

import android.view.View;

import com.yuehai.android.R;
import com.yuehai.android.contract.AddUserContract;
import com.yuehai.android.presenter.AddUserPresenter;

import butterknife.OnClick;
import library.base.BaseMvpActivity;

/**
 * Created by zhaoyuehai 2019/3/22
 */
public class AddUserActivity extends BaseMvpActivity<AddUserContract.Presenter> implements AddUserContract.View {


    @Override
    protected AddUserContract.Presenter createPresenter() {
        return new AddUserPresenter(this);
    }

    @Override
    protected int getInnerViewId() {
        return R.layout.activity_user_add;
    }

    @Override
    protected int getToolbarLayoutId() {
        return R.layout.user_add_toolbar;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.add_user;
    }

    @Override
    protected int getTitleId() {
        return R.id.title_tv;
    }

    @OnClick({R.id.add_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_tv:
                //添加用户

                break;
        }
    }
}
