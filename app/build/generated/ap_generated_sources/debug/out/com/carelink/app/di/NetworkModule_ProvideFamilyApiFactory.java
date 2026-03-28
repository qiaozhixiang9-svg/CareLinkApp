package com.carelink.app.di;

import com.carelink.app.data.remote.api.FamilyApi;
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
public final class NetworkModule_ProvideFamilyApiFactory implements Factory<FamilyApi> {
  private final NetworkModule module;

  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideFamilyApiFactory(NetworkModule module,
      Provider<Retrofit> retrofitProvider) {
    this.module = module;
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public FamilyApi get() {
    return provideFamilyApi(module, retrofitProvider.get());
  }

  public static NetworkModule_ProvideFamilyApiFactory create(NetworkModule module,
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideFamilyApiFactory(module, retrofitProvider);
  }

  public static FamilyApi provideFamilyApi(NetworkModule instance, Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(instance.provideFamilyApi(retrofit));
  }
}
