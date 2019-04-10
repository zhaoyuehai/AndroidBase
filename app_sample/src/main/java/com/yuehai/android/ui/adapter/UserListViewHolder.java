package com.yuehai.android.ui.adapter;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yuehai.android.R;
import com.yuehai.android.net.response.UserForListBean;
import com.yuehai.android.widget.recyclerhelper.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by zhaoyuehai 2019/3/28
 */
public class UserListViewHolder extends BaseViewHolder<UserForListBean> {
    @BindView(R.id.name_tv)
    TextView nameTV;
    @BindView(R.id.email_tv)
    TextView emailTV;
    @BindView(R.id.phone_tv)
    TextView phoneTV;
    @BindView(R.id.delete_btn)
    Button deleteBTN;
    private OnDeleteClickListener mOnDeleteClickListener;

    public UserListViewHolder(ViewGroup parent, OnDeleteClickListener onDeleteClickListener) {
        super(parent, R.layout.user_list_item);
        this.mOnDeleteClickListener = onDeleteClickListener;
    }

    @Override
    public void setData(UserForListBean userBean) {
        super.setData(userBean);
        nameTV.setText(String.format("姓名:%s", userBean.getUserName()));
        phoneTV.setText(String.format("手机:%s", userBean.getPhone()));
        emailTV.setText(String.format("邮箱:%s", userBean.getEmail()));
        deleteBTN.setOnClickListener(v -> mOnDeleteClickListener.onDeleteClick(getAdapterPosition()));
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }
}
