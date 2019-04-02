package com.yuehai.android.presenter;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.yuehai.android.RxUtils;
import com.yuehai.android.contract.UserListContract;
import com.yuehai.android.net.ApiUtil;
import com.yuehai.android.net.ResultObserver;
import com.yuehai.android.vo.ResultBean;
import com.yuehai.android.vo.UserBean;
import com.yuehai.android.widget.TipDialogFragment;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.disposables.Disposable;
import library.base.BasePresenter;

/**
 * 用户列表 P
 * Created by zhaoyuehai 2019/3/22
 */
public class UserListPresenter extends BasePresenter<UserListContract.View> implements UserListContract.Presenter {

    public UserListPresenter(UserListContract.View view) {
        super(view);
    }

    private int pageNum = 1;

    @Override
    protected void onCreate() {
        super.onCreate();
        if (isViewAttached()) getView().showLoading();
        loadData();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        pageNum++;
        loadData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        pageNum = 1;
        loadData();
    }

    private void loadData() {
        ApiUtil.getInstance()
                .getApiService()
                .getUsers(pageNum, 10)
                .compose(RxUtils.io_main())
                .subscribe(new ResultObserver<ResultBean<List<UserBean>>>(this) {

                    @Override
                    public void onNext(ResultBean<List<UserBean>> listResultBean) {
                        if (isViewAttached()) {
                            getView().showData(listResultBean, pageNum == 1);
                            if (listResultBean.getData().size() == 0 & pageNum > 1) pageNum--;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().showData(null, pageNum == 1);
                            if (pageNum > 1) pageNum--;
                            getView().dismissLoading();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (isViewAttached()) getView().dismissLoading();
                    }
                });
    }

    @Override
    public void onLongClick(UserBean userBean) {
        if (isViewAttached()) {
            getView().alterConfirmDialog("确定要删除 " + userBean.getUserName() + " 吗？", new TipDialogFragment.OnClickListener() {
                @Override
                public void onConfirm() {
                    delete(userBean);
                }

                @Override
                public void onCancel() {

                }
            });
        }
    }

    private void delete(UserBean userBean) {
        ApiUtil.getInstance()
                .getApiService()
                .deleteUser(userBean.getId())
                .compose(RxUtils.io_main())
                .subscribe(new ResultObserver<ResultBean<String>>(this) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        if (isViewAttached()) getView().showLoading();
                    }

                    @Override
                    public void onNext(ResultBean<String> stringResultBean) {
                        if (isViewAttached() && stringResultBean.getData().equals("1")) {
                            getView().onDeleteSuccess(userBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isViewAttached()) {
                            getView().dismissLoading();
                            getView().showToast("删除失败");
                        }
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        if (isViewAttached()) getView().dismissLoading();
                    }
                });

    }
}
