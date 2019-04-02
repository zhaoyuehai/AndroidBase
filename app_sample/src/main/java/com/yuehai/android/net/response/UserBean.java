package com.yuehai.android.net.response;

/**
 * Created by zhaoyuehai 2019/3/28
 */
public class UserBean {
    //    {"id":1,"status":2,"userName":"zhaoyuehai","password":"$2a$10$fFD570Fh8UR.beVDRIJykuCOTY6JV5RizJlklIYQQMCxIe8py4W5S","email":"zhaoyuehai5282@163.com","nickName":"月海","phone":"18511073583","createTime":null,"avatar":null}
    private String avatar;
    private String createTime;
    private String email;
    private String userName;
    private String nickName;
    private String phone;
    private int status;
    private long id;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
