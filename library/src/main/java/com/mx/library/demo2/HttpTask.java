package com.mx.library.demo2;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class HttpTask<T> implements Runnable, Delayed {

    private IHttpRequest mIHttpRequest;

    public HttpTask(T requestData, String url, IHttpRequest httpRequest, CallBackListener callBackListener) {

        this.mIHttpRequest = httpRequest;
        httpRequest.setUrl(url);

        String content = JSON.toJSONString(requestData);
        try {
            httpRequest.setData(content.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpRequest.setListener(callBackListener);

    }

    @Override
    public void run() {
        try {
            mIHttpRequest.execute();
        } catch (Exception e) {
            //将重试的任务添加到重试队列中
            ThreadPoolManager.getInstance().addDelalyTask(this);
        }

    }

    private long delaTime;
    private int retryCount;

    public long getDelaTime() {
        return delaTime;
    }

    public void setDelaTime(long delaTime) {
        this.delaTime = System.currentTimeMillis() + delaTime;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    @Override
    public long getDelay(@NonNull TimeUnit unit) {
        return unit.convert(this.delaTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NonNull Delayed o) {
        return 0;
    }
}
