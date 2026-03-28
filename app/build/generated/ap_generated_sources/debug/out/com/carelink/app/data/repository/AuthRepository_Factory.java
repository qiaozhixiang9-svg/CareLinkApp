package com.carelink.app.data.repository;

import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.data.remote.api.AuthApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class AuthRepository_Factory implements Factory<AuthRepository> {
  private final Provider<AuthApi> authApiProvider;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  public AuthRepository_Factory(Provider<AuthApi> authApiProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    this.authApiProvider = authApiProvider;
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  @Override
  public AuthRepository get() {
    return newInstance(authApiProvider.get(), preferenceManagerProvider.get());
  }

  public static AuthRepository_Factory create(Provider<AuthApi> authApiProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new AuthRepository_Factory(authApiProvider, preferenceManagerProvider);
  }

  public static AuthRepository newInstance(AuthApi authApi, PreferenceManager preferenceManager) {
    return new AuthRepository(authApi, preferenceManager);
  }
}
