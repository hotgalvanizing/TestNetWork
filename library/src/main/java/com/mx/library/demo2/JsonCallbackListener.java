package com.mx.library.demo2;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonCallbackListener<T> implements CallBackListener {

    private Class<T> clazz;
    private IJsonDataListener mJsonDataListener;

    Handler handler = new Handler(Looper.getMainLooper());

    public JsonCallbackListener(Class<T> clazz,IJsonDataListener listener) {
        this.clazz = clazz;
        this.mJsonDataListener = listener;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        String response = getContent(inputStream);
        final T cla = JSON.parseObject(response, clazz);
        handler.post(new Runnable() {
            @Override
            public void run() {
                mJsonDataListener.onSuccess(cla);
            }
        });
    }

    private String getContent(InputStream inputStream) {
        String content = null;
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

        } catch (IOException io) {

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return sb.toString();
    }


    @Override
    public void onFailure() {
        mJsonDataListener.onFailure();
    }
}
