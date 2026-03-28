package com.carelink.app.di;

import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.data.remote.api.AuthApi;
import com.carelink.app.data.repository.AuthRepository;
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
public final class RepositoryModule_ProvideAuthRepositoryFactory implements Factory<AuthRepository> {
  private final RepositoryModule module;

  private final Provider<AuthApi> authApiProvider;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  public RepositoryModule_ProvideAuthRepositoryFactory(RepositoryModule module,
      Provider<AuthApi> authApiProvider, Provider<PreferenceManager> preferenceManagerProvider) {
    this.module = module;
    this.authApiProvider = authApiProvider;
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  @Override
  public AuthRepository get() {
    return provideAuthRepository(module, authApiProvider.get(), preferenceManagerProvider.get());
  }

  public static RepositoryModule_ProvideAuthRepositoryFactory create(RepositoryModule module,
      Provider<AuthApi> authApiProvider, Provider<PreferenceManager> preferenceManagerProvider) {
    return new RepositoryModule_ProvideAuthRepositoryFactory(module, authApiProvider, preferenceManagerProvider);
  }

  public static AuthRepository provideAuthRepository(RepositoryModule instance, AuthApi authApi,
      PreferenceManager preferenceManager) {
    return Preconditions.checkNotNullFromProvides(instance.provideAuthRepository(authApi, preferenceManager));
  }
}
