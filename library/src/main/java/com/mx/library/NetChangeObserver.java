package com.mx.library;

public interface NetChangeObserver {

    void onConnect(NetType netType);

    void onDisConnect();
}
