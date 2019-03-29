package com.yuehai.android.ui;

import android.view.View;
import android.widget.EditText;

import com.yuehai.android.R;
import com.yuehai.android.contract.RegisterContract;
import com.yuehai.android.presenter.RegisterPresenter;
import com.yuehai.android.util.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;
import library.base.BaseMvpActivity;

/**
 * Created by zhaoyuehai 2019/3/22
 */
public class RegisterActivity extends BaseMvpActivity<RegisterContract.Presenter> implements RegisterContract.View {


    @Override
    protected RegisterContract.Presenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int getInnerViewId() {
        return R.layout.activity_user_register;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.register;
    }

    @BindView(R.id.et_1)
    EditText editText1;
    @BindView(R.id.et_2)
    EditText editText2;

    @OnClick({R.id.register_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_btn:
                //添加用户
                if (editText1.getText() == null || editText1.getText().toString().equals("")) {
                    editText1.setError("请输入用户名");
                    editText1.requestFocus();
                    return;
                }
                if (editText2.getText() == null || editText2.getText().toString().equals("")) {
                    editText2.setError("请输入密码");
                    editText2.requestFocus();
                    return;
                }
                String userName = editText1.getText().toString();
                String password = editText2.getText().toString();
                presenter.onRegister(userName, password);
                break;
        }
    }
}
