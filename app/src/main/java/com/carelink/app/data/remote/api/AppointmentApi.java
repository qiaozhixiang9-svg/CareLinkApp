package com.carelink.app.data.remote.api;

import com.carelink.app.data.remote.dto.BaseResponse;
import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;
import java.util.Map;

public interface AppointmentApi {
    @GET("/appointments")
    Call<BaseResponse<List<Map<String, Object>>>> getAppointments(@Query("elderId") long elderId);

    @POST("/appointments")
    Call<BaseResponse<Long>> createAppointment(@Body Map<String, Object> body);

    @PUT("/appointments/{id}")
    Call<BaseResponse<Void>> updateAppointment(@Path("id") long id, @Body Map<String, Object> body);

    @DELETE("/appointments/{id}")
    Call<BaseResponse<Void>> deleteAppointment(@Path("id") long id);
}
