package com.carelink.app.di;

import com.carelink.app.data.remote.api.AlertApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class NetworkModule_ProvideAlertApiFactory implements Factory<AlertApi> {
  private final NetworkModule module;

  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideAlertApiFactory(NetworkModule module,
      Provider<Retrofit> retrofitProvider) {
    this.module = module;
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public AlertApi get() {
    return provideAlertApi(module, retrofitProvider.get());
  }

  public static NetworkModule_ProvideAlertApiFactory create(NetworkModule module,
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideAlertApiFactory(module, retrofitProvider);
  }

  public static AlertApi provideAlertApi(NetworkModule instance, Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(instance.provideAlertApi(retrofit));
  }
}
