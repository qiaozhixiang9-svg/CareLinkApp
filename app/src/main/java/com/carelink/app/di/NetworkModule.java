package com.carelink.app.di;

import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.data.remote.api.AuthApi;
import com.carelink.app.data.remote.api.AlertApi;
import com.carelink.app.data.remote.api.AppointmentApi;
import com.carelink.app.data.remote.api.CheckinApi;
import com.carelink.app.data.remote.api.FamilyApi;
import com.carelink.app.data.remote.api.NoteApi;
import com.carelink.app.data.remote.api.ShiftApi;
import com.carelink.app.data.remote.service.AuthInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    private static final String BASE_URL = "https://api.carelink.com/";

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    public AuthInterceptor provideAuthInterceptor(PreferenceManager preferenceManager) {
        return new AuthInterceptor(preferenceManager);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
                                             AuthInterceptor authInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides @Singleton
    public AuthApi provideAuthApi(Retrofit retrofit) { return retrofit.create(AuthApi.class); }

    @Provides @Singleton
    public AlertApi provideAlertApi(Retrofit retrofit) { return retrofit.create(AlertApi.class); }

    @Provides @Singleton
    public AppointmentApi provideAppointmentApi(Retrofit retrofit) { return retrofit.create(AppointmentApi.class); }

    @Provides @Singleton
    public CheckinApi provideCheckinApi(Retrofit retrofit) { return retrofit.create(CheckinApi.class); }

    @Provides @Singleton
    public FamilyApi provideFamilyApi(Retrofit retrofit) { return retrofit.create(FamilyApi.class); }

    @Provides @Singleton
    public NoteApi provideNoteApi(Retrofit retrofit) { return retrofit.create(NoteApi.class); }

    @Provides @Singleton
    public ShiftApi provideShiftApi(Retrofit retrofit) { return retrofit.create(ShiftApi.class); }
}
