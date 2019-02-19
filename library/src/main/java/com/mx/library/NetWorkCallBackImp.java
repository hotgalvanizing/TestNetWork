package com.mx.library;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * onAvailable,onLost 成对出现
 * <p>
 * 生硬的断开可能不会回调onLosing
 * <p>
 * onCapabilitiesChanged可能会回调多次，使用需要谨慎，避免重复操作
 * <p>
 * <p>
 * NetworkCapabilities.TRANSPORT_WIFI 一样可以判断网络类型
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NetWorkCallBackImp extends ConnectivityManager.NetworkCallback {

    @Override
    public void onAvailable(Network network) {
        super.onAvailable(network);
        Log.d(Constants.TAG, "连接");
    }

    @Override
    public void onLost(Network network) {
        super.onLost(network);
        Log.d(Constants.TAG, "终端");
    }

    @Override
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);
        Log.d(Constants.TAG, "变更");
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.d(Constants.TAG, "WIFI");
            } else {
                Log.d(Constants.TAG, "other");
            }
        }

    }

    @Override
    public void onLosing(Network network, int maxMsToLive) {
        super.onLosing(network, maxMsToLive);
    }
}
