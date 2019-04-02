package com.yuehai.android.ui;

import android.content.Intent;
import android.view.View;

import com.yuehai.android.R;

import butterknife.OnClick;
import library.base.BaseActivity;

/**
 * 主页
 * Created by zhaoyuehai 2019/3/22
 */
public class MainActivity extends BaseActivity {

    @Override
    protected int getInnerViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.main;
    }

    @Override
    protected boolean exitAnimalEnable() {
        return false;
    }

    @OnClick({R.id.test_btn1, R.id.test_btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_btn1:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
            case R.id.test_btn2:
                startActivity(new Intent(MainActivity.this, UserListActivity.class));
                break;
        }
    }
}
