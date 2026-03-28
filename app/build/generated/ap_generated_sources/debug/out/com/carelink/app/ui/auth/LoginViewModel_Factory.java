package com.carelink.app.ui.auth;

import com.carelink.app.data.local.pref.PreferenceManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
  private final Provider<PreferenceManager> preferenceManagerProvider;

  public LoginViewModel_Factory(Provider<PreferenceManager> preferenceManagerProvider) {
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  @Override
  public LoginViewModel get() {
    return newInstance(preferenceManagerProvider.get());
  }

  public static LoginViewModel_Factory create(
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new LoginViewModel_Factory(preferenceManagerProvider);
  }

  public static LoginViewModel newInstance(PreferenceManager preferenceManager) {
    return new LoginViewModel(preferenceManager);
  }
}
