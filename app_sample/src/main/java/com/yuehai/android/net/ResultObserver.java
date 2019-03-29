package com.yuehai.android.net;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableContainer;
import library.base.utils.ToastUtil;
import retrofit2.HttpException;

/**
 * Created by zhaoyuehai 2019/3/29
 */
public abstract class ResultObserver<T> implements Observer<T> {
    private Disposable disposable;
    private DisposableContainer container;
    private boolean showErrorToast;//请求异常时 是否弹土司警告

    public ResultObserver(DisposableContainer container) {
        this(container, false);
    }

    public ResultObserver(DisposableContainer container, boolean showErrorToast) {
        this.container = container;
        this.showErrorToast = showErrorToast;
    }

    /**
     * 请求的开始，可以做显示Progress操作，并在onError方法 和 （onComplete方法 或者 onNext方法）取消Progress
     *
     * @param d disposable
     */
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable = d;
        if (container != null)
            container.add(disposable);
    }

    /**
     * 请求失败
     *
     * @param e if(e instanceof ApiException){//我们的自定义异常  数据正常返回情况 子类去处理
     *          <p>
     *          onError 方法如果执行，onComplete方法将不会执行！
     */
    @Override
    public void onError(Throwable e) {
        if (showErrorToast) {
            String message;
//            if (e instanceof ApiException) {//自定义异常
//                message = e.getMessage();
//            } else
            if (e instanceof ConnectException) {//手机没连上网的情况
                message = "网络异常，请检查网络后重试！";
            } else if (e instanceof HttpException) {//404 500 服务器连接错误
                message = "服务器连接异常！";
            } else if (e instanceof SocketTimeoutException) {//连上网，连接超时无响应
                message = "对不起，连接超时！";
            } else {
                message = "请求失败";
            }
            ToastUtil.showToast(message);
        }
        clearDisposable();
    }


    /**
     * 请求成功之后完成调用
     * onComplete 方法如果执行，onError方法将不会执行！
     */
    @Override
    public void onComplete() {
        clearDisposable();
    }

    /**
     * 移除并中止此Disposable
     */
    private void clearDisposable() {
        if (container != null && disposable != null) {
            container.remove(disposable);
            container = null;
        }
    }
}
