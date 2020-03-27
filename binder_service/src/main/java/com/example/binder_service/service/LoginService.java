package com.example.binder_service.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.binder_service.ILogin;
import com.example.binder_service.bean.LoginBean;

/**
 * @ClassName LoginService
 * @Description TODO
 * @Author danke
 * @Date 2020/3/27 1:53 PM
 * @Version 1.0
 */
public class LoginService extends Service {
    private LoginBean mLoginBean;
    private final String correctName = "danke";
    private final String correctPassword = "love";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    /**
     * ILogin 如果没有引用，rebuild一下即可，查看build文件里面有没有aidl_source_output_dir...
     */
    ILogin.Stub iBinder = new ILogin.Stub() {
        @Override
        public void login(LoginBean loginBean) throws RemoteException {
            mLoginBean = loginBean;
        }

        @Override
        public void getLoginBean(LoginBean loginBean) throws RemoteException {
            if (loginBean != null) {
                Toast.makeText(LoginService.this, loginBean.toString(), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public boolean checkLoginStatus() throws RemoteException {
            if (mLoginBean != null) {
                return correctName.equals(mLoginBean.getLoginName()) && correctPassword.equals(mLoginBean.getLoginPassword());
            }
            return false;
        }
    };


}
