package com.yuehai.android;

import com.yuehai.android.net.response.UserBean;
import com.yuehai.android.util.LogUtil;
import com.yuehai.android.util.SPUtil;

import java.util.Date;

public class UserData {
    private static String IS_LOGIN = "SP_IS_LOGIN";

    private static String TOKEN = "SP_USER_TOKEN";
    private static String REFRESH_TOKEN = "SP_USER_REFRESH_TOKEN";
    private static String TOKEN_HEADER = "SP_USER_TOKEN_HEADER";
    private static String EXPIRATION = "SP_USER_EXPIRATION";
    private static String ROLE_CODE = "SP_USER_ROLE_CODE";
    private static String USER_NAME = "SP_USER_USER_NAME";
    private static String NICK_NAME = "SP_USER_NICK_NAME";
    private static String PHONE = "SP_USER_PHONE";
    private static String EMAIL = "SP_USER_EMAIL";
    private static String AVATAR = "SP_USER_AVATAR";
    private static String STATUS = "SP_USER_STATUS";
    private static String USER_ID = "SP_USER_USER_ID";


    private UserData() {
    }

    public static UserData getInstance() {
        return ClassHolder.INSTANCE;
    }

    /**
     * roldCode对应的角色名称
     *
     * @param roleCode 角色代号
     * @return 角色名称
     */
    public static String getRoleNameByCode(int roleCode) {
        switch (roleCode) {
            case 0:
                return "临时用户";
            case 1:
                return "管理员";
            case 2:
                return "普通用户";
            case 3:
                return "VIP用户";
            default:
                return "未知";
        }
    }

    private static class ClassHolder {
        private static final UserData INSTANCE = new UserData();
    }

    private UserBean user;

    public UserBean getUser() {
        if (user == null) {
            SPUtil spUtil = SPUtil.getInstance(Contacts.SP_NAME);
            boolean isLogin = spUtil.getBoolean(IS_LOGIN, false);
            if (isLogin) {
                user = new UserBean();
                user.setToken(spUtil.getString(TOKEN));
                user.setRefreshToken(spUtil.getString(REFRESH_TOKEN));
                user.setTokenHeader(spUtil.getString(TOKEN_HEADER));
                user.setExpiration(spUtil.getLong(EXPIRATION));
                user.setRoleCode(spUtil.getInt(ROLE_CODE, -1));
                user.setUserName(spUtil.getString(USER_NAME));
                user.setNickName(spUtil.getString(NICK_NAME));
                user.setPhone(spUtil.getString(PHONE));
                user.setEmail(spUtil.getString(EMAIL));
                user.setAvatar(spUtil.getString(AVATAR));
                user.setStatus(spUtil.getInt(STATUS, -1));
                user.setUserId(spUtil.getLong(USER_ID, -1L));
            }
        }
        return user;
    }

    public void saveUser(UserBean userBean) {
        this.user = userBean;
        LogUtil.e("======保存的过期时间是：" + new Date(user.getExpiration()));
        SPUtil spUtil = SPUtil.getInstance(Contacts.SP_NAME);
        spUtil.put(IS_LOGIN, true);
        spUtil.put(TOKEN, userBean.getToken());
        spUtil.put(REFRESH_TOKEN, userBean.getRefreshToken());
        spUtil.put(TOKEN_HEADER, userBean.getTokenHeader());
        spUtil.put(EXPIRATION, userBean.getExpiration());
        spUtil.put(ROLE_CODE, userBean.getRoleCode());
        spUtil.put(USER_NAME, userBean.getUserName());
        spUtil.put(NICK_NAME, userBean.getNickName());
        spUtil.put(PHONE, userBean.getPhone());
        spUtil.put(EMAIL, userBean.getEmail());
        spUtil.put(AVATAR, userBean.getAvatar());
        spUtil.put(STATUS, userBean.getStatus());
        spUtil.put(USER_ID, userBean.getUserId());
    }

    void clearUser() {
        SPUtil spUtil = SPUtil.getInstance(Contacts.SP_NAME);
        spUtil.put(IS_LOGIN, false);
        spUtil.remove(TOKEN);
        spUtil.remove(TOKEN_HEADER);
        spUtil.remove(USER_NAME);
        spUtil.remove(NICK_NAME);
        spUtil.remove(PHONE);
        spUtil.remove(EMAIL);
        spUtil.remove(AVATAR);
        spUtil.remove(STATUS);
        spUtil.remove(USER_ID);
        this.user = null;
    }
}
