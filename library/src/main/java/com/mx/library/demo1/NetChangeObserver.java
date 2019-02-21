package com.mx.library.demo1;

public interface NetChangeObserver {

    void onConnect(NetType netType);

    void onDisConnect();
}
