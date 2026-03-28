package com.carelink.app.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.data.repository.AlertRepository;
import dagger.internal.DaggerGenerated;
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
public final class AlertDetectWorker_Factory {
  private final Provider<AlertRepository> alertRepositoryProvider;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  public AlertDetectWorker_Factory(Provider<AlertRepository> alertRepositoryProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    this.alertRepositoryProvider = alertRepositoryProvider;
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  public AlertDetectWorker get(Context context, WorkerParameters params) {
    return newInstance(context, params, alertRepositoryProvider.get(), preferenceManagerProvider.get());
  }

  public static AlertDetectWorker_Factory create(Provider<AlertRepository> alertRepositoryProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new AlertDetectWorker_Factory(alertRepositoryProvider, preferenceManagerProvider);
  }

  public static AlertDetectWorker newInstance(Context context, WorkerParameters params,
      AlertRepository alertRepository, PreferenceManager preferenceManager) {
    return new AlertDetectWorker(context, params, alertRepository, preferenceManager);
  }
}
