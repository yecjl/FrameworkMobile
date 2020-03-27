// ILogin.aidl
package com.example.binder_service;

import com.example.binder_service.bean.LoginBean;

// Declare any non-default types here with import statements

interface ILogin {
   void login(in LoginBean loginBean);
   void getLoginBean(out LoginBean loginBean);
   boolean checkLoginStatus();
}
