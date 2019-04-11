package com.yuehai.android.net;

/**
 * 自定义异常
 * Created by zhaoyuehai 2018/7/30
 */
public class ApiException extends RuntimeException {
    private int code;//403用户缺少权限

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}