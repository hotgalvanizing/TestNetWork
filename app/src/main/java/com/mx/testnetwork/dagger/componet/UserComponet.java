package com.mx.testnetwork.dagger.componet;

import com.mx.testnetwork.Main6Activity;
import com.mx.testnetwork.dagger.model.UserModule;

import dagger.Component;

@Component(modules = {UserModule.class})
public interface UserComponet {

    void inject(Main6Activity activity);
}
