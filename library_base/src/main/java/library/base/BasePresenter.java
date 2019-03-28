package library.base;


import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * MVP中的Presenter基类
 * <p>
 * 使用了{@link CompositeDisposable}维护RxJava订阅
 * 也可以考虑用{@link io.reactivex.internal.disposables.DisposableHelper}
 * <p>
 */

public abstract class BasePresenter<V> implements IBasePresenter {

    //网络请求容器
    private CompositeDisposable mCompositeDisposable;

    //防止view的内存泄露，弱引用
    private Reference<V> mViewRef; //View接口类型的弱引用

    public BasePresenter(V view) {
        mViewRef = new WeakReference<>(view); //建立关联
    }

    protected V getView() {
        if (isViewAttached()) {
            return mViewRef.get();
        }
        return null; //获取View
    }

    protected boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null; //判断是否与View建立关联
    }

    /**
     * 在Activity/Fragment的onCreate()方法执行完成后执行此方法
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
        if (isViewAttached()) {
            mViewRef.clear(); //解除关联
            mViewRef = null;
        }
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

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
}
