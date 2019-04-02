package com.yuehai.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yuehai.android.R;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import library.base.BaseActivity;

/**
 * 主页
 * Created by zhaoyuehai 2019/3/22
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.test_btn3)
    Button test3;

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
        test3.setText("图表测试");
    }

    @OnClick({R.id.test_btn1, R.id.test_btn2, R.id.test_btn3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_btn1:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
            case R.id.test_btn2:
                startActivity(new Intent(MainActivity.this, UserListActivity.class));
                break;
            case R.id.test_btn3:
                Intent intent = new Intent(MainActivity.this, ChartDemoActivity.class);
                intent.putExtra("title", test3.getText().toString());
                startActivity(intent);
                break;
        }
    }
}
