package com.yuehai.android.presenter;

import com.yuehai.android.RxUtils;
import com.yuehai.android.contract.UserListContract;
import com.yuehai.android.net.ApiUtil;
import com.yuehai.android.net.ResultObserver;
import com.yuehai.android.vo.ResultBean;
import com.yuehai.android.vo.UserBean;

import java.util.List;

import io.reactivex.disposables.Disposable;
import library.base.BasePresenter;

/**
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
        loadData(1, 10);
    }

    private void loadData(int pageNum, int pageSize) {
        ApiUtil.getInstance()
                .getApiService()
                .users(pageNum, pageSize)
                .compose(RxUtils.io_main())
                .subscribe(new ResultObserver<ResultBean<List<UserBean>>>(this) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (isViewAttached()) getView().showLoading();
                    }

                    @Override
                    public void onNext(ResultBean<List<UserBean>> listResultBean) {
                        if (isViewAttached()) getView().showData(listResultBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().showData(null);
                            getView().dismissLoading();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (isViewAttached()) getView().dismissLoading();
                    }
                });
    }
}
