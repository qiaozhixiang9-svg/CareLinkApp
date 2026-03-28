package com.carelink.app.di;

import com.carelink.app.data.remote.service.AuthInterceptor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

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
public final class NetworkModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final NetworkModule module;

  private final Provider<HttpLoggingInterceptor> loggingInterceptorProvider;

  private final Provider<AuthInterceptor> authInterceptorProvider;

  public NetworkModule_ProvideOkHttpClientFactory(NetworkModule module,
      Provider<HttpLoggingInterceptor> loggingInterceptorProvider,
      Provider<AuthInterceptor> authInterceptorProvider) {
    this.module = module;
    this.loggingInterceptorProvider = loggingInterceptorProvider;
    this.authInterceptorProvider = authInterceptorProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideOkHttpClient(module, loggingInterceptorProvider.get(), authInterceptorProvider.get());
  }

  public static NetworkModule_ProvideOkHttpClientFactory create(NetworkModule module,
      Provider<HttpLoggingInterceptor> loggingInterceptorProvider,
      Provider<AuthInterceptor> authInterceptorProvider) {
    return new NetworkModule_ProvideOkHttpClientFactory(module, loggingInterceptorProvider, authInterceptorProvider);
  }

  public static OkHttpClient provideOkHttpClient(NetworkModule instance,
      HttpLoggingInterceptor loggingInterceptor, AuthInterceptor authInterceptor) {
    return Preconditions.checkNotNullFromProvides(instance.provideOkHttpClient(loggingInterceptor, authInterceptor));
  }
}
