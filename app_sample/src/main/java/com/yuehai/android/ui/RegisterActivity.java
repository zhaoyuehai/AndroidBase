package com.yuehai.android.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.yuehai.android.R;
import com.yuehai.android.contract.RegisterContract;
import com.yuehai.android.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import library.base.BaseMvpActivity;

/**
 * 注册 V
 * Created by zhaoyuehai 2019/3/22
 */
public class RegisterActivity extends BaseMvpActivity<RegisterContract.Presenter> implements RegisterContract.View {


    @Override
    protected RegisterContract.Presenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int getInnerViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.register;
    }

    @BindView(R.id.et_0)
    EditText editText0;
    @BindView(R.id.et_1)
    EditText editText1;
    @BindView(R.id.et_2)
    EditText editText2;

    @OnClick({R.id.register_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_btn:
                //添加用户
                if (TextUtils.isEmpty(editText0.getText().toString().trim())) {
                    editText1.setError("请输入手机号");
                    editText1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(editText1.getText().toString().trim())) {
                    editText1.setError("请输入用户名");
                    editText1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(editText2.getText().toString().trim())) {
                    editText2.setError("请输入密码");
                    editText2.requestFocus();
                    return;
                }
                String phone = editText0.getText().toString().trim();
                String userName = editText1.getText().toString().trim();
                String password = editText2.getText().toString().trim();
                presenter.onRegister(userName, password, phone);
                break;
        }
    }

    @Override
    public void onRegisterSuccess(String username) {
        Intent intent = new Intent();
        intent.putExtra("username", username);
        setResult(RESULT_OK, intent);
        finish();
    }
}