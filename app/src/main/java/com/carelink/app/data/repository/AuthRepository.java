package com.carelink.app.data.repository;

import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.data.remote.api.AuthApi;
import com.carelink.app.data.remote.dto.BaseResponse;
import com.carelink.app.data.remote.dto.LoginRequest;
import com.carelink.app.data.remote.dto.LoginResponse;
import com.carelink.app.data.remote.dto.SendCodeRequest;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class AuthRepository {

    private final AuthApi authApi;
    private final PreferenceManager preferenceManager;

    @Inject
    public AuthRepository(AuthApi authApi, PreferenceManager preferenceManager) {
        this.authApi = authApi;
        this.preferenceManager = preferenceManager;
    }

    public interface ResultCallback<T> {
        void onSuccess(T data);
        void onError(String message);
    }

    public void sendCode(String phone, ResultCallback<Void> callback) {
        authApi.sendCode(new SendCodeRequest(phone)).enqueue(new Callback<BaseResponse<Void>>() {
            @Override
            public void onResponse(Call<BaseResponse<Void>> call, Response<BaseResponse<Void>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("发送验证码失败");
                }
            }
            @Override
            public void onFailure(Call<BaseResponse<Void>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void login(String phone, String code, ResultCallback<LoginResponse> callback) {
        authApi.login(new LoginRequest(phone, code)).enqueue(new Callback<BaseResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<LoginResponse>> call,
                                   Response<BaseResponse<LoginResponse>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    LoginResponse data = response.body().getData();
                    preferenceManager.saveToken(data.getToken());
                    preferenceManager.saveUserId(data.getUserId());
                    preferenceManager.savePhone(data.getPhone());
                    callback.onSuccess(data);
                } else {
                    callback.onError("登录失败，请检查验证码");
                }
            }
            @Override
            public void onFailure(Call<BaseResponse<LoginResponse>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void logout() {
        authApi.logout().enqueue(new Callback<BaseResponse<Void>>() {
            @Override public void onResponse(Call<BaseResponse<Void>> call, Response<BaseResponse<Void>> response) {}
            @Override public void onFailure(Call<BaseResponse<Void>> call, Throwable t) {}
        });
        preferenceManager.clear();
    }
}
