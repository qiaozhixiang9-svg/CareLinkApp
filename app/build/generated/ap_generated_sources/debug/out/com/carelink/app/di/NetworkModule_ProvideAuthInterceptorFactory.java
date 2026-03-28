package com.carelink.app.di;

import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.data.remote.service.AuthInterceptor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class NetworkModule_ProvideAuthInterceptorFactory implements Factory<AuthInterceptor> {
  private final NetworkModule module;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  public NetworkModule_ProvideAuthInterceptorFactory(NetworkModule module,
      Provider<PreferenceManager> preferenceManagerProvider) {
    this.module = module;
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  @Override
  public AuthInterceptor get() {
    return provideAuthInterceptor(module, preferenceManagerProvider.get());
  }

  public static NetworkModule_ProvideAuthInterceptorFactory create(NetworkModule module,
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new NetworkModule_ProvideAuthInterceptorFactory(module, preferenceManagerProvider);
  }

  public static AuthInterceptor provideAuthInterceptor(NetworkModule instance,
      PreferenceManager preferenceManager) {
    return Preconditions.checkNotNullFromProvides(instance.provideAuthInterceptor(preferenceManager));
  }
}
