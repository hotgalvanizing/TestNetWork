package com.mx.testnetwork.dagger;

public class UserManager {

    UserStroe mUserStroe;
    ApiService mApiService;

    public UserManager(UserStroe mUserStroe, ApiService mApiService) {
        this.mUserStroe = mUserStroe;
        this.mApiService = mApiService;
    }

    public void register() {
        mApiService.register();
        mUserStroe.login();
    }


}
