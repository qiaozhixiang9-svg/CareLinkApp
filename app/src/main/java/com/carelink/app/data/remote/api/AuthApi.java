package com.carelink.app.data.remote.api;

import com.carelink.app.data.remote.dto.BaseResponse;
import com.carelink.app.data.remote.dto.LoginRequest;
import com.carelink.app.data.remote.dto.LoginResponse;
import com.carelink.app.data.remote.dto.SendCodeRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("/auth/sendCode")
    Call<BaseResponse<Void>> sendCode(@Body SendCodeRequest request);

    @POST("/auth/login")
    Call<BaseResponse<LoginResponse>> login(@Body LoginRequest request);

    @POST("/auth/logout")
    Call<BaseResponse<Void>> logout();

    @POST("/family/create")
    Call<BaseResponse<Long>> createFamily(@Body java.util.Map<String, String> body);

    @POST("/family/join")
    Call<BaseResponse<Void>> joinFamily(@Body java.util.Map<String, String> body);
}
