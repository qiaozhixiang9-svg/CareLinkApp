package com.carelink.app.ui.alert;

import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.data.repository.AlertRepository;
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
public final class AlertViewModel_Factory implements Factory<AlertViewModel> {
  private final Provider<AlertRepository> alertRepositoryProvider;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  public AlertViewModel_Factory(Provider<AlertRepository> alertRepositoryProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    this.alertRepositoryProvider = alertRepositoryProvider;
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  @Override
  public AlertViewModel get() {
    return newInstance(alertRepositoryProvider.get(), preferenceManagerProvider.get());
  }

  public static AlertViewModel_Factory create(Provider<AlertRepository> alertRepositoryProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new AlertViewModel_Factory(alertRepositoryProvider, preferenceManagerProvider);
  }

  public static AlertViewModel newInstance(AlertRepository alertRepository,
      PreferenceManager preferenceManager) {
    return new AlertViewModel(alertRepository, preferenceManager);
  }
}
