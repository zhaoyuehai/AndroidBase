package library.base.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import library.base.BaseApplication;


public class ToastUtil {

    private static String oldMsg;
    private static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    public static void showToast(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            if (toast == null) {
                toast = Toast.makeText(BaseApplication.getInstance(), msg, Toast.LENGTH_SHORT);
                toast.show();
                oneTime = System.currentTimeMillis();
            } else {
                twoTime = System.currentTimeMillis();
                if (msg.equals(oldMsg)) {
                    if (twoTime - oneTime > 1500) {
                        toast.show();
                    }
                } else {
                    oldMsg = msg;
                    toast.setText(msg);
                    toast.show();
                }
            }
            oneTime = twoTime;
        }
    }

    public static void showToast(int resId) {
        showToast(BaseApplication.getInstance().getString(resId));
    }

    public static void showToast(Context context, String msg, int lengthShort) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(context, msg, lengthShort).show();
        }
    }

}
