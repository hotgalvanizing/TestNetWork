package com.mx.library;

import android.app.Application;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkManager.getInstance().init(this);
    }
}
