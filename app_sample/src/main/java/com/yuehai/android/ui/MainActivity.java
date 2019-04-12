package com.yuehai.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yuehai.android.MyApplication;
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
    @BindView(R.id.test_btn0)
    Button test0;
    @BindView(R.id.test_btn1)
    Button test1;
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
        test3.setText(R.string.chart_demo);
        test4.setText(R.string.theme_setting);
        checkUser();
    }

    private void checkUser() {
        UserBean user = UserData.getInstance().getUser();
        if (user != null) {
            test0.setVisibility(View.GONE);
            test1.setVisibility(View.VISIBLE);
            testTV.setVisibility(View.VISIBLE);
            testTV.setText(String.format("当前用户：%s【角色：%s】", user.getUserName(), UserData.getRoleNameByCode(user.getRoleCode())));
        } else {
            test0.setVisibility(View.VISIBLE);
            test1.setVisibility(View.GONE);
            testTV.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.test_btn0, R.id.test_btn1, R.id.test_btn2, R.id.test_btn3, R.id.test_btn4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_btn0:
                startActivityForResult(new Intent(this, LoginActivity.class), 1000);
                break;
            case R.id.test_btn1:
                MyApplication.clearUser();
                checkUser();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {//登录成功
            checkUser();
        }
    }
}
