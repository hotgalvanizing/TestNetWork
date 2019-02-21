package com.mx.library;

import android.app.Application;

import com.mx.library.demo1.NetWorkManager;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkManager.getInstance().init(this);
    }
}
