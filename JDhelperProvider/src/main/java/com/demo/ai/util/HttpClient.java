package com.demo.ai.util;

import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author quanlin
 * @date 2019 2019/3/11 17:52
 **/
public class HttpClient {
    private volatile static OkHttpClient httpClient;

    public static OkHttpClient getHttpClient() {
        if (null == httpClient) {
            synchronized (HttpClient.class) {
                if (null == httpClient) {
                    Dispatcher dispatcher = new Dispatcher();
                    dispatcher.setMaxRequests(1000 * 2);
                    dispatcher.setMaxRequestsPerHost(1000 * 2);

                    ConnectionPool pool = new ConnectionPool(50, 5000, TimeUnit.MILLISECONDS);
                    
                    int timeout = 16000;
                    httpClient = new OkHttpClient.Builder()
                            .dispatcher(dispatcher)
                            .connectionPool(pool)
                            .readTimeout(timeout, TimeUnit.MILLISECONDS)
                            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
                            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                            .retryOnConnectionFailure(true)
                            .build();
                }
            }
        }

        return httpClient;
    }
}
