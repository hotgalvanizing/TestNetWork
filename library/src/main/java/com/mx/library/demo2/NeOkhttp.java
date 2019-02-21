package com.mx.library.demo2;

public class NeOkhttp {

    public static<T,M> void sendJsonRequest(T requesrData,String url,Class<M> response,IJsonDataListener listener){
        IHttpRequest httpRequest = new JsonHttpRequest();
        CallBackListener callBackListener = new JsonCallbackListener<>(response,listener);
        HttpTask httpTask = new HttpTask(requesrData,url,httpRequest,callBackListener);
        ThreadPoolManager.getInstance().addTask(httpTask);
    }
}
