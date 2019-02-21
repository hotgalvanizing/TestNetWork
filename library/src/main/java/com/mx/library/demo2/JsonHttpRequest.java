package com.mx.library.demo2;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonHttpRequest implements IHttpRequest {

    private String mUrl;
    private byte[] data;
    private CallBackListener callBackListener;
    private HttpURLConnection urlConnection;

    @Override
    public void setUrl(String url) {
        this.mUrl = url;
    }

    @Override
    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public void setListener(CallBackListener listener) {
        this.callBackListener = listener;
    }

    @Override
    public void execute() {

        URL url = null;

        try {
            url = new URL(mUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(6000);
            urlConnection.setUseCaches(false);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setReadTimeout(3000);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            urlConnection.connect();

            OutputStream out = urlConnection.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);
            bos.write(data);
            bos.flush();
            out.close();
            bos.close();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = urlConnection.getInputStream();
                callBackListener.onSuccess(in);
            }else {
                //访问失败重试
                throw new RuntimeException("请求失败");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
    }
}
