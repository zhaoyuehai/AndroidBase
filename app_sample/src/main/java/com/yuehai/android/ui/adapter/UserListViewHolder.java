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
    @BindView(R.id.nick_tv)
    TextView nickTV;
    @BindView(R.id.email_tv)
    TextView emailTV;
    @BindView(R.id.phone_tv)
    TextView phoneTV;
    @BindView(R.id.delete_btn)
    Button deleteBTN;
    @BindView(R.id.modify_btn)
    Button modifyBTN;
    private OnEditClickListener mOnEditClickListener;

    public UserListViewHolder(ViewGroup parent, OnEditClickListener onEditClickListener) {
        super(parent, R.layout.user_list_item);
        this.mOnEditClickListener = onEditClickListener;
    }

    @Override
    public void setData(UserForListBean userBean) {
        super.setData(userBean);
        nameTV.setText(String.format("用户名:%s", userBean.getUserName()));
        nickTV.setText(String.format("昵称:%s", userBean.getNickName()));
        phoneTV.setText(String.format("手机:%s", userBean.getPhone()));
        emailTV.setText(String.format("邮箱:%s", userBean.getEmail()));
        deleteBTN.setOnClickListener(v -> mOnEditClickListener.onDeleteClick(getAdapterPosition()));
        modifyBTN.setOnClickListener(v -> mOnEditClickListener.onModifyClick(getAdapterPosition()));
    }

    public interface OnEditClickListener {
        void onDeleteClick(int position);

        void onModifyClick(int position);
    }
}
