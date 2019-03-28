package library.base;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.Nullable;

import library.base.utils.ProgressDialogUtil;
import library.base.utils.ToastUtil;

/**
 * MVP模式Activity的基类【MVP模式有点过度架构】
 */
public abstract class BaseMvpActivity<P extends IBasePresenter> extends BaseActivity implements IBaseView {

    protected P presenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    protected Dialog loadingDialog;

    /**
     * 弹出加载中dialog
     * <p>
     * 如果需要带文字的dialog，重写此方法即可--参考-->LoginActivity
     */
    @Override
    public void showLoading() {
        if (null == loadingDialog) {
            loadingDialog = ProgressDialogUtil.getProgressDialog(this);
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