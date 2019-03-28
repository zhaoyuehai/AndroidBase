package library.base.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

/**
 * Created by zhaoyuehai 2019/3/19
 */
public class ProgressDialogUtil {
    /**
     * 弹出进度对话框
     *
     * @param context Context
     * @return Dialog
     */
    public static Dialog getProgressDialog(Context context) {
        //默认触摸不消失，返回键消失
        return ProgressDialogUtil.getProgressDialog(context, null, true, false);
    }

    /**
     * 弹出进度对话框
     *
     * @param context Context
     * @param text    文字提示
     * @return Dialog
     */
    public static Dialog getProgressDialog(Context context, String text) {
        //默认触摸不消失，返回键消失
        return ProgressDialogUtil.getProgressDialog(context, text, true, false);
    }

    /**
     * 弹出进度对话框
     *
     * @param context Context
     * @return Dialog
     */
    public static Dialog getProgressDialog(Context context, @StringRes int textId) {
        //默认触摸不消失，返回键消失
        return ProgressDialogUtil.getProgressDialog(context, textId, true, false);
    }

    /**
     * 弹出进度对话框
     *
     * @param context     Context
     * @param cancelable  按返回键是否取消
     * @param cancelTouch 触摸是否取消
     * @return Dialog
     */
    public static Dialog getProgressDialog(Context context, @StringRes int textId, boolean cancelable, boolean cancelTouch) {
        Dialog progress = new MyProgressDialog(context, textId);
        progress.setCancelable(cancelable);
        progress.setCanceledOnTouchOutside(cancelTouch);
        return progress;
    }


    /**
     * 弹出进度对话框
     *
     * @param context     Context
     * @param text        文字提示
     * @param cancelable  按返回键是否取消
     * @param cancelTouch 触摸是否取消
     * @return Dialog
     */
    public static Dialog getProgressDialog(Context context, @Nullable String text, boolean cancelable, boolean cancelTouch) {
        Dialog progress = text != null ? new MyProgressDialog(context, text) : new MyProgressDialog(context);
        progress.setCancelable(cancelable);
        progress.setCanceledOnTouchOutside(cancelTouch);
        return progress;
    }

    /**
     * 取消对话框
     *
     * @param dialog DialogInterface
     */
    public static void dismissDialog(DialogInterface dialog) {
        if (dialog != null) dialog.dismiss();
    }
}
