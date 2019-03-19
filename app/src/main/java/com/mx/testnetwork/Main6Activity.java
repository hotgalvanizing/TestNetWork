package com.mx.testnetwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.mx.testnetwork.dagger.ApiService;
import com.mx.testnetwork.dagger.UserManager;
import com.mx.testnetwork.dagger.componet.DaggerUserComponet;
import com.mx.testnetwork.dagger.model.UserModule;

import javax.inject.Inject;
import javax.inject.Named;

public class Main6Activity extends AppCompatActivity {

//    @Inject
//    ApiService mApiService;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main6);
//        DaggerUserComponet.create().inject(this);
//        mApiService.register();
//        Log.d("TAG", "onCreate: " + mApiService);
//        ((TextView) findViewById(R.id.text6)).setText(mApiService.toString());
//    }

//    @Inject
//    UserManager mManager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main6);
//        DaggerUserComponet.builder().userModule(new UserModule(this)).build().inject(this);
//        mManager.register();
//        ((TextView) findViewById(R.id.text6)).setText(mManager.toString());
//        Log.d("TAG", "onCreate: " + mManager);
//    }

    @Named("dev")
    @Inject
    ApiService mApiService;
    @Named("release")
    @Inject
    ApiService mApiService1;
    private boolean is_Dev = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        DaggerUserComponet.builder().userModule(new UserModule(this)).build().inject(this);
        Log.d("TAG", "mApiService= " + mApiService);
        Log.d("TAG", "mApiService1= " + mApiService1);
        if (is_Dev) {
            mApiService.register();
        } else {
            mApiService.register();
        }
    }


}
