package com.example.binder_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.binder_service.ILogin;
import com.example.binder_service.bean.LoginBean;


public class MainActivity extends AppCompatActivity {

    private ILogin iLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edtName = findViewById(R.id.edt_name);
        final EditText edtPassword = findViewById(R.id.edt_pwd);
        Button btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String password = edtPassword.getText().toString();

                try {
                    iLogin.login(new LoginBean(name, password));
                    if (iLogin.checkLoginStatus()) {
                        Toast.makeText(MainActivity.this, "登录成功！~", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                    }
                    iLogin.getLoginBean(new LoginBean("danke", "111111"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "调用失败", Toast.LENGTH_LONG).show();
                }
            }
        });

        bindService();
    }

    private void bindService() {
        Intent intent = new Intent();

        // ComponentName，顾名思义，就是组建名称，通过调用 Intent 中的 setComponent 方法，我们可以打开另外一个应用中的 Activity 或者服务。
        intent.setComponent(new ComponentName(
                "com.example.binder_service", // 第一个参数是要启动应用的包名称，这个包名称是指 AndroidManifest 文件中列出的应用的包名称
                "com.example.binder_service.service.LoginService")); // 第二个参数是你要启动的 Activity 或者 Service 的全称（包名+类名）
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iLogin = ILogin.Stub.asInterface(service);
            Log.d("danke", "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("danke", "onServiceDisconnected: ");
        }
    };
}
