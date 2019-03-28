package library.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import library.base.utils.ProgressDialogUtil;
import library.base.utils.ToastUtil;

/**
 * MVP模式Fragment的基类
 */
public abstract class BaseMvpFragment<P extends IBasePresenter> extends Fragment implements IBaseView {

    protected P presenter;

    protected abstract P createPresenter();

    private Unbinder unbinder;

    protected abstract @LayoutRes
    int getContentViewId();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = createPresenter();
        if (presenter == null)
            throw new NullPointerException();
        getLifecycle().addObserver(presenter);
        initBaseData();
        initView();
    }

    /**
     * 基本数据初始化（如：Intent传递进来的数据等等）
     */
    protected void initBaseData() {
    }

    /**
     * 界面布局初始化
     */
    protected void initView() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null && unbinder != Unbinder.EMPTY) unbinder.unbind();
    }

    private Dialog loadingDialog;

    @Override
    public void showLoading() {
        if (getContext() == null) return;
        if (null == loadingDialog) {
            loadingDialog = ProgressDialogUtil.getProgressDialog(getContext());
        }
        loadingDialog.show();
    }

    @Override
    public void dismissLoading() {
        ProgressDialogUtil.dismissDialog(loadingDialog);
    }

    @Override
    public void showToast(String message) {
        ToastUtil.showToast(message);
    }

    @Override
    public void showToast(int strId) {
        ToastUtil.showToast(strId);
    }

}
