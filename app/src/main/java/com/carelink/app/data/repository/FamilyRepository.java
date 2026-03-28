package com.carelink.app.data.repository;

import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.data.remote.api.FamilyApi;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FamilyRepository {

    private final FamilyApi familyApi;
    private final PreferenceManager preferenceManager;

    @Inject
    public FamilyRepository(FamilyApi familyApi, PreferenceManager preferenceManager) {
        this.familyApi = familyApi;
        this.preferenceManager = preferenceManager;
    }

    public void getMembers(long familyId, retrofit2.Callback<com.carelink.app.data.remote.dto.BaseResponse<java.util.List<java.util.Map<String, Object>>>> callback) {
        familyApi.getMembers(familyId).enqueue(callback);
    }

    public void getElders(long familyId, retrofit2.Callback<com.carelink.app.data.remote.dto.BaseResponse<java.util.List<java.util.Map<String, Object>>>> callback) {
        familyApi.getElders(familyId).enqueue(callback);
    }
}
