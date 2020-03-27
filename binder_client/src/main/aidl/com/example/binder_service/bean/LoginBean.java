package com.example.binder_service.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @ClassName LoginBean
 * @Description TODO
 * @Author danke
 * @Date 2020/3/27 1:46 PM
 * @Version 1.0
 */
public class LoginBean implements Parcelable {
    private String loginName;
    private String loginPassword;

    public LoginBean(String loginName, String loginPassword) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
    }

    /**
     * 必须要添加，否则输出 aidl out LoginBean 会报错
     */
    public LoginBean(){}

    protected LoginBean(Parcel in) {
        loginName = in.readString();
        loginPassword = in.readString();
    }

    public static final Creator<LoginBean> CREATOR = new Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel in) {
            return new LoginBean(in);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(loginName);
        dest.writeString(loginPassword);
    }

    /**
     * 会找不到 loginBean.readFromParcel(_reply);
     * @param in
     */
    public void readFromParcel(Parcel in) {
        loginName = in.readString();
        loginPassword = in.readString();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "loginName='" + loginName + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                '}';
    }
}

