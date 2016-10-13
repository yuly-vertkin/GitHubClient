/*
 * Copyright (c) New Cloud Technologies, Ltd., 2013-2016
 *
 * You can not use the contents of the file in any way without New Cloud Technologies, Ltd. written permission.
 * To obtain such a permit, you should contact New Cloud Technologies, Ltd. at http://ncloudtech.com/contact.html
 *
 */

package com.example.yuly.githubclient;

import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubServiceManager {
    private static GitHubService service;
    private static String credentials;

    public static GitHubService getService() {
        if (service == null) {
            final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            okHttpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();

                    if (!TextUtils.isEmpty(credentials)) {
                        Request.Builder requestBuilder = request.newBuilder()
                            .header("Authorization", credentials)
                            .header("Accept", "application/json")
                            .method(request.method(), request.body());
                        request = requestBuilder.build();
                    }
                    return chain.proceed(request);
                }
            });

            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(
                    new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
                .build();

            service = retrofit.create(GitHubService.class);
        }
        return service;
    }

    public static void setCredentials(String userName, String password) {
        if (userName != null && password != null) {
            String src = userName + ":" + password;
            credentials = "Basic " + Base64.encodeToString(src.getBytes(), Base64.NO_WRAP);
        }
    }
}
