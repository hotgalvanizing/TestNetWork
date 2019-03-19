package com.mx.testnetwork.dagger.model;

import android.content.Context;
import android.util.Log;

import com.mx.testnetwork.dagger.ApiService;
import com.mx.testnetwork.dagger.UserManager;
import com.mx.testnetwork.dagger.UserStroe;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    Context context;

    public UserModule(Context context) {
        this.context = context;
    }

    @Provides
    @Named("dev")
    public ApiService provideApiServiceDev(String url) {
        ApiService apiService = new ApiService(url);
        Log.d("TAG", "provideApiServiceDev: " + apiService);
        return apiService;
    }

    @Provides
    @Named("release")
    public ApiService provideApiServiceRelease() {
        ApiService apiService = new ApiService(context);
        Log.d("TAG", "provideApiServiceRelease: " + apiService);
        return apiService;
    }


    @Provides
    public String providerUrl() {
        return "www.baidu.com";
    }

    @Provides
    public UserManager provideUserManager(ApiService apiService, UserStroe userStroe) {
        return new UserManager(userStroe, apiService);
    }

}
