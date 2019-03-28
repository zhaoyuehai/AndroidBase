package com.yuehai.android.ui;

import android.view.ViewGroup;

import com.yuehai.android.R;
import com.yuehai.android.contract.UserListContract;
import com.yuehai.android.presenter.UserListPresenter;
import com.yuehai.android.ui.adapter.UserListViewHolder;
import com.yuehai.android.vo.UserBean;
import com.yuehai.android.vo.UserListBean;
import com.yuehai.android.widget.recyclerhelper.CommonRecycleAdapter;
import com.yuehai.android.widget.recyclerhelper.BaseViewHolder;
import com.yuehai.android.widget.recyclerhelper.MyDividerItemDecoration;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import library.base.BaseMvpActivity;

/**
 * Created by zhaoyuehai 2019/3/22
 */
public class UserListActivity extends BaseMvpActivity<UserListContract.Presenter> implements UserListContract.View {

    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;
    private CommonRecycleAdapter<UserBean> adapter;

    @Override
    protected UserListContract.Presenter createPresenter() {
        return new UserListPresenter(this);
    }

    @Override
    protected int getInnerViewId() {
        return R.layout.activity_user_list;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.user_list;
    }

    @Override
    protected void initView() {
        super.initView();
        adapter = new CommonRecycleAdapter<UserBean>() {
            @NonNull
            @Override
            public BaseViewHolder<UserBean> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new UserListViewHolder(parent);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, MyDividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showData(UserListBean result) {
        if (result != null && result.getData() != null)
            adapter.addAll(result.getData());
    }
}
