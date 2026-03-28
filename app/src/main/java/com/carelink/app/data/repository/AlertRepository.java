package com.carelink.app.data.repository;

import androidx.lifecycle.LiveData;
import com.carelink.app.data.local.dao.AlertDao;
import com.carelink.app.data.local.entity.AlertEventEntity;
import com.carelink.app.data.local.entity.AlertRuleEntity;
import com.carelink.app.data.remote.api.AlertApi;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class AlertRepository {

    private final AlertDao alertDao;
    private final AlertApi alertApi;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Inject
    public AlertRepository(AlertDao alertDao, AlertApi alertApi) {
        this.alertDao = alertDao;
        this.alertApi = alertApi;
    }

    public LiveData<List<AlertEventEntity>> getPendingAlerts(long elderId) {
        return alertDao.getPendingAlerts(elderId);
    }

    public LiveData<List<AlertEventEntity>> getAlertEvents(long elderId) {
        return alertDao.getAlertEvents(elderId);
    }

    public List<AlertRuleEntity> getEnabledRules(long elderId) {
        return alertDao.getEnabledRules(elderId);
    }

    public void saveAlert(AlertEventEntity entity) {
        executor.execute(() -> alertDao.insertEvent(entity));
    }

    public void handleAlert(long alertId, String status) {
        executor.execute(() -> {
            alertDao.updateStatus(alertId, status);
            Map<String, String> body = new HashMap<>();
            body.put("status", status);
            alertApi.handleAlert(alertId, body).enqueue(new Callback<com.carelink.app.data.remote.dto.BaseResponse<Void>>() {
                @Override public void onResponse(Call<com.carelink.app.data.remote.dto.BaseResponse<Void>> call, Response<com.carelink.app.data.remote.dto.BaseResponse<Void>> response) {}
                @Override public void onFailure(Call<com.carelink.app.data.remote.dto.BaseResponse<Void>> call, Throwable t) {}
            });
        });
    }

    public void triggerEmergency(long elderId, double lat, double lng) {
        Map<String, Object> body = new HashMap<>();
        body.put("elderId", elderId);
        body.put("lat", lat);
        body.put("lng", lng);
        body.put("timestamp", System.currentTimeMillis());
        alertApi.triggerEmergency(body).enqueue(new Callback<com.carelink.app.data.remote.dto.BaseResponse<Void>>() {
            @Override public void onResponse(Call<com.carelink.app.data.remote.dto.BaseResponse<Void>> call, Response<com.carelink.app.data.remote.dto.BaseResponse<Void>> response) {}
            @Override public void onFailure(Call<com.carelink.app.data.remote.dto.BaseResponse<Void>> call, Throwable t) {}
        });
    }
}
