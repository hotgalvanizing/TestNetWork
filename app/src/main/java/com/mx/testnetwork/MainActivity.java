package com.mx.testnetwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mx.library.Constants;
import com.mx.library.NetChangeObserver;
import com.mx.library.NetType;
import com.mx.library.NetWork;
import com.mx.library.NetWorkManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetWorkManager.getInstance().registerOberver(this);
//        NetWorkManager.getInstance().init(getApplication());
//        NetWorkManager.getInstance().setListener(this);
    }

//    @Override
//    public void onConnect(NetType netType) {
//        Log.d(Constants.TAG, "连接了");
//    }
//
//    @Override
//    public void onDisConnect() {
//        Log.d(Constants.TAG, "失去连接了");
//    }

    @NetWork(netType = NetType.WIFI)
    public void netWork(NetType netType){
        switch (netType){
            case WIFI:
                Log.d(Constants.TAG, "WIFI");
                break;
            case CMNET:
                Log.d(Constants.TAG, "CMNET");
                break;
            case CMWAP:
                Log.d(Constants.TAG, "CMWAP");
                break;
            case NONE:
                Log.d(Constants.TAG, "NONE");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetWorkManager.getInstance().unRegisterObserver(this);
    }
}
