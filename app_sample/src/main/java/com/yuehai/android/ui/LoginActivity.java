package com.yuehai.android.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.yuehai.android.R;
import com.yuehai.android.contract.LoginContract;
import com.yuehai.android.presenter.LoginPresenter;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import library.base.BaseMvpActivity;

/**
 * Login V
 */
public class LoginActivity extends BaseMvpActivity<LoginContract.Presenter> implements LoginContract.View {

    @Override
    protected int getToolbarTitle() {
        return R.string.login;
    }

    @Override
    protected int getInnerViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    @BindView(R.id.name_et)
    EditText userNameEt;
    @BindView(R.id.pwd_et)
    EditText passwordEt;

    @Override
    public void initUserName(String username) {
        userNameEt.setText(username);
        passwordEt.requestFocus();
    }

    @Override
    public void onLoginSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @OnClick({R.id.login_btn, R.id.register_go_tv, R.id.find_pwd_tv})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.find_pwd_tv:
                startActivity(new Intent(this, SetPwdActivity.class));
                break;
            case R.id.register_go_tv:
                startActivityForResult(new Intent(this, RegisterActivity.class), 2000);
                break;
            case R.id.login_btn:
                String userName = userNameEt.getText().toString().trim();
                if (TextUtils.isEmpty(userName)) {
                    userNameEt.setError("请输入用户名");
                    userNameEt.requestFocus();
                    return;
                }
                String password = passwordEt.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    passwordEt.setError("请输入密码");
                    passwordEt.requestFocus();
                    return;
                }
                presenter.login(userName, password);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000 && resultCode == RESULT_OK) {
            if (data != null) {
                String username = data.getStringExtra("username");
                if (username != null) {
                    userNameEt.setText(username);
                    passwordEt.requestFocus();
                }
            }
        }
    }
}
