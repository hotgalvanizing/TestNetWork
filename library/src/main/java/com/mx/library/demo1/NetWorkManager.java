package com.mx.library.demo1;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Build;

public class NetWorkManager {

    private static volatile NetWorkManager instance;
    private Application application;
    private NetStateReceiver receiver;

    private NetWorkManager() {
        receiver = new NetStateReceiver();
    }

    public static NetWorkManager getInstance() {
        if (instance == null) {
            synchronized (NetWorkManager.class) {
                if (instance == null) {
                    instance = new NetWorkManager();
                }

            }
        }
        return instance;
    }

    public Application getApplication() {
        if (application == null) {
            throw new RuntimeException("");
        }
        return application;
    }

    @SuppressLint("MissingPermission")
    public void init(Application application) {
        this.application = application;

        //做广播注册
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(Constants.ANDROID_NET_CHANGE_ACTION);
//        application.registerReceiver(receiver, filter);

        //第二种方式不通过广播
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ConnectivityManager.NetworkCallback networkCallback = new NetWorkCallBackImp();
            NetworkRequest.Builder builder =new NetworkRequest.Builder();
            NetworkRequest request = builder.build();
            ConnectivityManager connectivityManager = (ConnectivityManager) NetWorkManager.getInstance().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null){
                connectivityManager.registerNetworkCallback(request,networkCallback);
            }else {

            }
        }
    }

//    public void setListener(NetChangeObserver listener) {
//        receiver.setListener(listener);
//    }

    public void unRegisterObserver(Object o) {
        receiver.unRegisterObserver(o);
    }

    public void registerOberver(Object o) {
        receiver.registerObserver(o);
    }
}
