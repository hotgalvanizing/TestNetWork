package com.mx.library.demo2;

public interface IJsonDataListener<T> {

    void onSuccess(T m);

    void onFailure();
}
