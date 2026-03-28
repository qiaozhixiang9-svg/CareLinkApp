package com.carelink.app.data.remote.service;

import com.carelink.app.data.local.pref.PreferenceManager;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/** 自动添加 Bearer Token 的拦截器 */
public class AuthInterceptor implements Interceptor {

    private final PreferenceManager preferenceManager;

    public AuthInterceptor(PreferenceManager preferenceManager) {
        this.preferenceManager = preferenceManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = preferenceManager.getToken();
        Request original = chain.request();

        if (token.isEmpty()) {
            return chain.proceed(original);
        }

        Request request = original.newBuilder()
                .header("Authorization", "Bearer " + token)
                .build();
        return chain.proceed(request);
    }
}
