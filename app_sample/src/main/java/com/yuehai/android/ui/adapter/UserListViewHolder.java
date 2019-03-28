package com.yuehai.android.ui.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.yuehai.android.R;
import com.yuehai.android.vo.UserBean;
import com.yuehai.android.widget.recyclerhelper.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by zhaoyuehai 2019/3/28
 */
public class UserListViewHolder extends BaseViewHolder<UserBean> {
    @BindView(R.id.name_tv)
    TextView nameTV;
    @BindView(R.id.email_tv)
    TextView emailTV;
    @BindView(R.id.phone_tv)
    TextView phoneTV;

    public UserListViewHolder(ViewGroup parent) {
        super(parent, R.layout.user_list_item);
    }

    @Override
    public void setData(UserBean userBean) {
        super.setData(userBean);
        nameTV.setText(String.format("姓名:%s", userBean.getUserName()));
        phoneTV.setText(String.format("手机:%s", userBean.getPhone()));
        emailTV.setText(String.format("邮箱:%s", userBean.getEmail()));
    }
}
