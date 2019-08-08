package com.yuehai.android.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    @BindView(R.id.register_ll)
    View registerLL;
    @BindView(R.id.et_1)
    EditText userNameET;
    @BindView(R.id.et_2)
    EditText pwdET;
    @BindView(R.id.et_3)
    EditText phoneET;
    @BindView(R.id.modify_ll)
    View modifyLL;
    @BindView(R.id.tv_1)
    TextView userNameTV;
    @BindView(R.id.et_4)
    EditText nickNameET;
    @BindView(R.id.et_5)
    EditText phone2ET;
    @BindView(R.id.et_6)
    EditText emailET;
    @BindView(R.id.confirm_btn)
    Button confirmBtn;

    private boolean isModify = false;
    private long id = -1;

    @Override
    protected void initView() {
        super.initView();
        isModify = getIntent().getBooleanExtra("isModify", false);
        if (isModify) {
            setToolbarTitle("修改");
            confirmBtn.setText(R.string.commit);
            modifyLL.setVisibility(View.VISIBLE);
            registerLL.setVisibility(View.GONE);
            id = getIntent().getLongExtra("id", -1L);
            String userName = getIntent().getStringExtra("userName");
            String phone = getIntent().getStringExtra("phone");
            if (id != -1 && userName != null && phone != null) {
                userNameTV.setText(String.format("用户名：%s", userName));
                phone2ET.setText(phone);
                String email = getIntent().getStringExtra("email");
                if (email != null) {
                    emailET.setText(email);
                }
                String nickName = getIntent().getStringExtra("nickName");
                if (nickName != null) {
                    nickNameET.setText(nickName);
                }
            }
        }
    }

    @OnClick({R.id.confirm_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm_btn://添加用户
                if (isModify) {
                    if (id == -1) return;
                    String phone = phone2ET.getText().toString().trim();
                    if (TextUtils.isEmpty(phone)) {
                        phone2ET.setError("请输入手机号");
                        phone2ET.requestFocus();
                        return;
                    }
                    String email = emailET.getText().toString().trim();
                    String nickName = nickNameET.getText().toString().trim();
                    presenter.onModifyUser(id, phone, email, nickName);
                } else {
                    String userName = userNameET.getText().toString().trim();
                    if (TextUtils.isEmpty(userName)) {
                        userNameET.setError("请输入用户名");
                        userNameET.requestFocus();
                        return;
                    }
                    String password = pwdET.getText().toString().trim();
                    if (TextUtils.isEmpty(password)) {
                        pwdET.setError("请输入密码");
                        pwdET.requestFocus();
                        return;
                    }
                    String phone = phoneET.getText().toString().trim();
                    if (TextUtils.isEmpty(phone)) {
                        phoneET.setError("请输入手机号");
                        phoneET.requestFocus();
                        return;
                    }
                    presenter.onRegister(userName, password, phone);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRegisterSuccess(String username, Long userId) {
        Intent intent = new Intent();
        intent.putExtra("username", username);
        intent.putExtra("userId", userId);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onModifySuccess() {
        Intent intent = new Intent();
        intent.putExtra("userId", id);
        setResult(RESULT_OK, intent);
        finish();
    }
}