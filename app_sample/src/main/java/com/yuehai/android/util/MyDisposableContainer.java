package com.yuehai.android.util;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableContainer;

/**
 * Created by zhaoyuehai 2019/4/4
 */
public class MyDisposableContainer implements DisposableContainer {


    //网络请求订阅管理容器
    private CompositeDisposable mCompositeDisposable;

    @Override
    public boolean add(Disposable d) {
        //如果解绑了的话添加,需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        return mCompositeDisposable.add(d);
    }

    /**
     * Removes and disposes the given disposable if it is part of this
     * container.
     * 执行delete(Disposable d)后，并调用该Disposable的dispose()方法
     * 先从容器中删掉，不再持有，然后去中止他。
     *
     * @param d the disposable to remove and dispose, not null
     * @return true if the operation was successful
     */
    @Override
    public boolean remove(Disposable d) {
        //如果解绑了的话添加,需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        return mCompositeDisposable.remove(d);
    }

    /**
     * Removes (but does not dispose) the given disposable if it is part of this
     * container.
     * 从容器【OpenHashSet<Disposable>】中删除此引用,不会调用该Disposable的dispose()方法）
     * 只是从容器中删掉，不再持有，不会去中止他。
     *
     * @param d the disposable to remove, not null
     * @return true if the operation was successful
     */
    @Override
    public boolean delete(Disposable d) {
        //如果解绑了的话添加,需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        return mCompositeDisposable.delete(d);
    }

    public void clear() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
