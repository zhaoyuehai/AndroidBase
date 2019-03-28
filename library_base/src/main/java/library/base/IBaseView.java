package library.base;

import androidx.annotation.StringRes;

/**
 * Created by zhaoyuehai 2019/3/15
 */
public interface IBaseView {
    /**
     * 显示加载弹框
     */
    void showLoading();

    /**
     * 隐藏加载弹框
     */
    void dismissLoading();

    /**
     * 弹提示
     *
     * @param msg 提示内容
     */
    void showToast(String msg);

    /**
     * 弹提示
     *
     * @param strId 提示内容
     */
    void showToast(@StringRes int strId);

}
