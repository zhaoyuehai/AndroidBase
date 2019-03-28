package com.yuehai.android.vo;

import java.util.List;

/**
 * Created by zhaoyuehai 2019/3/28
 */
public class UserListBean {
    private String code;
    private String message;
    private String serviceCode;
    private List<UserBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public List<UserBean> getData() {
        return data;
    }

    public void setData(List<UserBean> data) {
        this.data = data;
    }
}
