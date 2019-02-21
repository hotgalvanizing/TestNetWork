package com.mx.library.demo2;

import java.io.InputStream;

interface CallBackListener {

    void onSuccess(InputStream inputStream);

    void onFailure();
}
