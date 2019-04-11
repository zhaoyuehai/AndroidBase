package com.yuehai.android.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.yuehai.android.R;
import com.yuehai.android.contract.LoginContract;
import com.yuehai.android.presenter.LoginPresenter;

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
        userNameEt.setSelection(username.length());
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
                startActivity(new Intent(this, RegisterActivity.class));
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

}
