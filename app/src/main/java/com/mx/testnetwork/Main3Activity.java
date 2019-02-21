package com.mx.testnetwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mx.library.Constants;
import com.mx.library.demo2.IJsonDataListener;
import com.mx.library.demo2.NeOkhttp;
import com.mx.library.demo2.ResponseBean;

public class Main3Activity extends AppCompatActivity {

//    String url = "http://www.wanandroid.com//hotkey/json";
    String url = "xxxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        NeOkhttp.sendJsonRequest(null, url, ResponseBean.class, new IJsonDataListener<ResponseBean>() {
            @Override
            public void onSuccess(ResponseBean m) {
                Log.d(Constants.TAG,m.getData().get(0).getName());
            }

            @Override
            public void onFailure() {
                Log.d(Constants.TAG,"error");
            }
        });

    }
}
