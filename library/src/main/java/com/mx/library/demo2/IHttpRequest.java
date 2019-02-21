package com.mx.library.demo2;

public interface IHttpRequest {

    void setUrl(String url);

    void setData(byte[] data);

    void setListener(CallBackListener listener);

    void execute();
}
