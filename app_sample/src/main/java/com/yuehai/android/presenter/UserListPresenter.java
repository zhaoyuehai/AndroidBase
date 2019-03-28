package com.yuehai.android.presenter;

import com.google.gson.Gson;
import com.yuehai.android.BuildConfig;
import com.yuehai.android.contract.UserListContract;
import com.yuehai.android.vo.UserListBean;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import library.base.BasePresenter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by zhaoyuehai 2019/3/22
 */
public class UserListPresenter extends BasePresenter<UserListContract.View> implements UserListContract.Presenter {

    private final OkHttpClient mOkHttpClient;
    private final Gson mGson;

    public UserListPresenter(UserListContract.View view) {
        super(view);
        mOkHttpClient = new OkHttpClient();
        mGson = new Gson();
    }

    private int pageNum = 1;

    @Override
    protected void onCreate() {
        super.onCreate();
        loadData(1, 10);
    }

    private void loadData(int pageNum, int pageSize) {
        Observable.create((ObservableOnSubscribe<Response>) emitter -> {
            Request request = new Request.Builder()
                    .url(BuildConfig.BASE_URL + "/user/all?pageNum=" + pageNum + "&pageSize=" + pageSize)
                    .build();
            try {
                Response response = mOkHttpClient.newCall(request).execute();
                emitter.onNext(response);
                emitter.onComplete();
            } catch (IOException e) {
                e.printStackTrace();
                emitter.onError(e);
            }
        })
                .subscribeOn(Schedulers.io())
                .map(response -> {
                    ResponseBody body = response.body();
                    if (body != null) {
                        return mGson.fromJson(body.string(), UserListBean.class);
                    }
                    return null;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserListBean>() {
                    private Disposable mDisposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        this.mDisposable = d;
                        add(mDisposable);
                        if (isViewAttached()) getView().showLoading();
                    }

                    @Override
                    public void onNext(UserListBean result) {
                        if (isViewAttached()) getView().showData(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        delete(mDisposable);
                        if (isViewAttached()) {
                            getView().showData(null);
                            getView().dismissLoading();
                        }
                    }

                    @Override
                    public void onComplete() {
                        delete(mDisposable);
                        if (isViewAttached()) getView().dismissLoading();
                    }
                });
    }
}
