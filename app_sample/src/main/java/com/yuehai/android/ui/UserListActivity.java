package com.yuehai.android.ui;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yuehai.android.R;
import com.yuehai.android.contract.UserListContract;
import com.yuehai.android.net.response.ResultBean;
import com.yuehai.android.net.response.UserForListBean;
import com.yuehai.android.presenter.UserListPresenter;
import com.yuehai.android.ui.adapter.UserListViewHolder;
import com.yuehai.android.widget.TipDialogFragment;
import com.yuehai.android.widget.recyclerhelper.BaseViewHolder;
import com.yuehai.android.widget.recyclerhelper.CommonRecycleAdapter;
import com.yuehai.android.widget.recyclerhelper.MyDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import library.base.BaseMvpActivity;

/**
 * 用户列表 V
 * Created by zhaoyuehai 2019/3/22
 */
public class UserListActivity extends BaseMvpActivity<UserListContract.Presenter> implements UserListContract.View, UserListViewHolder.OnEditClickListener {

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
                return new UserListViewHolder(parent, UserListActivity.this);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, MyDividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                startActivityForResult(new Intent(this, RegisterActivity.class), 3000);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3000 && resultCode == RESULT_OK) {
            presenter.onRefresh(smartRL);
        }
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

    @Override
    public void onDeleteClick(int position) {
        if (adapter.getItemCount() > position) {
            presenter.onDeleteClick(adapter.getItem(position));
        }
    }

    @Override
    public void onModifyClick(int position) {
        if (adapter.getItemCount() > position) {
            UserForListBean item = adapter.getItem(position);
            Intent intent = new Intent(this, RegisterActivity.class);
            intent.putExtra("isModify", true);
            intent.putExtra("id", item.getId());
            intent.putExtra("userName", item.getUserName());
            intent.putExtra("phone", item.getPhone());
            intent.putExtra("email", item.getEmail());
            intent.putExtra("nickName", item.getNickName());
            startActivityForResult(intent, 3000);
        }
    }
}
