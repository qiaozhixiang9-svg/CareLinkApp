package com.carelink.app.ui.calendar;

import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.data.repository.AppointmentRepository;
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
public final class AppointmentViewModel_Factory implements Factory<AppointmentViewModel> {
  private final Provider<AppointmentRepository> appointmentRepositoryProvider;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  public AppointmentViewModel_Factory(Provider<AppointmentRepository> appointmentRepositoryProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    this.appointmentRepositoryProvider = appointmentRepositoryProvider;
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  @Override
  public AppointmentViewModel get() {
    return newInstance(appointmentRepositoryProvider.get(), preferenceManagerProvider.get());
  }

  public static AppointmentViewModel_Factory create(
      Provider<AppointmentRepository> appointmentRepositoryProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new AppointmentViewModel_Factory(appointmentRepositoryProvider, preferenceManagerProvider);
  }

  public static AppointmentViewModel newInstance(AppointmentRepository appointmentRepository,
      PreferenceManager preferenceManager) {
    return new AppointmentViewModel(appointmentRepository, preferenceManager);
  }
}
