package com.mx.testnetwork.dagger;

import android.content.Context;
import android.util.Log;

public class ApiService {

    Context context;

    public ApiService(Context context) {
    }

    public ApiService(String url) {
        Log.d("TAG", "ApiService: " + url);
    }

    public void register() {
        Log.d("TAG", "ApiService: ");
    }

}
