package com.mx.testnetwork.dagger;

import android.util.Log;

import javax.inject.Inject;

public class UserStroe {

    @Inject
    public UserStroe(String url) {
        Log.d("TAG", "UserStroe: " + url);
    }

    public void login() {
        Log.d("TAG", "UserStroe: ");
    }

}
