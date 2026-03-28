package com.carelink.app.data.remote.api;

import com.carelink.app.data.remote.dto.BaseResponse;
import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;
import java.util.Map;

public interface FamilyApi {
    @GET("/family/members")
    Call<BaseResponse<List<Map<String, Object>>>> getMembers(@Query("familyId") long familyId);

    @GET("/family/elders")
    Call<BaseResponse<List<Map<String, Object>>>> getElders(@Query("familyId") long familyId);

    @POST("/family/invite")
    Call<BaseResponse<Void>> inviteMember(@Body Map<String, Object> body);

    @DELETE("/family/members/{userId}")
    Call<BaseResponse<Void>> removeMember(@Path("userId") long userId);
}
