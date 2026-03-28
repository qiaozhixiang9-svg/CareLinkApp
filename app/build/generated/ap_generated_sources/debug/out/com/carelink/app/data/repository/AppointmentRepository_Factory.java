package com.carelink.app.data.repository;

import com.carelink.app.data.local.dao.AppointmentDao;
import com.carelink.app.data.remote.api.AppointmentApi;
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
public final class AppointmentRepository_Factory implements Factory<AppointmentRepository> {
  private final Provider<AppointmentDao> appointmentDaoProvider;

  private final Provider<AppointmentApi> appointmentApiProvider;

  public AppointmentRepository_Factory(Provider<AppointmentDao> appointmentDaoProvider,
      Provider<AppointmentApi> appointmentApiProvider) {
    this.appointmentDaoProvider = appointmentDaoProvider;
    this.appointmentApiProvider = appointmentApiProvider;
  }

  @Override
  public AppointmentRepository get() {
    return newInstance(appointmentDaoProvider.get(), appointmentApiProvider.get());
  }

  public static AppointmentRepository_Factory create(
      Provider<AppointmentDao> appointmentDaoProvider,
      Provider<AppointmentApi> appointmentApiProvider) {
    return new AppointmentRepository_Factory(appointmentDaoProvider, appointmentApiProvider);
  }

  public static AppointmentRepository newInstance(AppointmentDao appointmentDao,
      AppointmentApi appointmentApi) {
    return new AppointmentRepository(appointmentDao, appointmentApi);
  }
}
