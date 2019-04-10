package com.yuehai.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yuehai.android.R;
import com.yuehai.android.UserData;
import com.yuehai.android.net.response.UserBean;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import library.base.BaseActivity;

/**
 * 主页
 * Created by zhaoyuehai 2019/3/22
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.test_tv)
    TextView testTV;
    @BindView(R.id.test_btn3)
    Button test3;
    @BindView(R.id.test_btn4)
    Button test4;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserBean user = UserData.getInstance().getUser();
        if (user != null) {
            testTV.setText(String.format("当前用户：%s", user.getUserName()));
        }
        test3.setText(R.string.chart_demo);
        test4.setText(R.string.theme_setting);
    }

    @OnClick({R.id.test_btn1, R.id.test_btn2, R.id.test_btn3, R.id.test_btn4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_btn1:
                UserData.getInstance().clearUser();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.test_btn2:
                startActivity(new Intent(MainActivity.this, UserListActivity.class));
                break;
            case R.id.test_btn3:
                startActivity(new Intent(MainActivity.this, ChartDemoActivity.class));
                break;
            case R.id.test_btn4:
                startActivity(new Intent(MainActivity.this, ThemeSettingActivity.class));
                break;
        }
    }
}
