package com.yuehai.android.ui;

import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yuehai.android.R;
import com.yuehai.android.contract.UserListContract;
import com.yuehai.android.net.response.ResultBean;
import com.yuehai.android.net.response.UserBean;
import com.yuehai.android.net.response.UserForListBean;
import com.yuehai.android.presenter.UserListPresenter;
import com.yuehai.android.ui.adapter.UserListViewHolder;
import com.yuehai.android.widget.TipDialogFragment;
import com.yuehai.android.widget.recyclerhelper.BaseViewHolder;
import com.yuehai.android.widget.recyclerhelper.CommonRecycleAdapter;
import com.yuehai.android.widget.recyclerhelper.MyDividerItemDecoration;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import library.base.BaseMvpActivity;

/**
 * 用户列表 V
 * Created by zhaoyuehai 2019/3/22
 */
public class UserListActivity extends BaseMvpActivity<UserListContract.Presenter> implements UserListContract.View {

    @BindView(R.id.smart_rl)
    protected SmartRefreshLayout smartRL;
    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;
    private CommonRecycleAdapter<UserForListBean> adapter;
    private TipDialogFragment confirmDialog;

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
        smartRL.setOnRefreshLoadMoreListener(presenter);
        adapter = new CommonRecycleAdapter<UserForListBean>() {
            @NonNull
            @Override
            public BaseViewHolder<UserForListBean> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new UserListViewHolder(parent, UserListActivity.this::onDeleteClick);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, MyDividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showData(@Nullable ResultBean<List<UserForListBean>> result, boolean isClear) {
        if (isClear) adapter.clear();
        if (result != null) {
            if (result.getData().size() > 0) {
                adapter.addAll(result.getData());
                smartRL.finishLoadMore(true);
            } else {
                smartRL.finishLoadMoreWithNoMoreData();
            }
            smartRL.finishRefresh();
        } else {
            smartRL.finishLoadMore(false);
            smartRL.finishRefresh(false);
        }
    }

    @Override
    public void alterConfirmDialog(String msg, TipDialogFragment.OnClickListener onClickListener) {
        if (confirmDialog == null) {
            confirmDialog = (TipDialogFragment) getSupportFragmentManager().findFragmentByTag("confirmDialog");
            if (confirmDialog == null) {
                confirmDialog = new TipDialogFragment();
                confirmDialog.setCancelable(false);
            }
        }
        if (!confirmDialog.isAdded()) {
            confirmDialog.setOnConfirmListener(onClickListener);
            confirmDialog.setMessage(msg);
            confirmDialog.show(getSupportFragmentManager(), "confirmDialog");
        }
    }

    @Override
    public void onDeleteSuccess(UserForListBean userBean) {
        adapter.remove(userBean);
    }

    private void onDeleteClick(int position) {
        if (adapter.getItemCount() > position) {
            presenter.onLongClick(adapter.getItem(position));
        }
    }
}
