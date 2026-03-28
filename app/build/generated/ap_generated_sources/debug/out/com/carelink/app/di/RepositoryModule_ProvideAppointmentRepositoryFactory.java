package com.carelink.app.di;

import com.carelink.app.data.local.dao.AppointmentDao;
import com.carelink.app.data.remote.api.AppointmentApi;
import com.carelink.app.data.repository.AppointmentRepository;
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
public final class RepositoryModule_ProvideAppointmentRepositoryFactory implements Factory<AppointmentRepository> {
  private final RepositoryModule module;

  private final Provider<AppointmentDao> appointmentDaoProvider;

  private final Provider<AppointmentApi> appointmentApiProvider;

  public RepositoryModule_ProvideAppointmentRepositoryFactory(RepositoryModule module,
      Provider<AppointmentDao> appointmentDaoProvider,
      Provider<AppointmentApi> appointmentApiProvider) {
    this.module = module;
    this.appointmentDaoProvider = appointmentDaoProvider;
    this.appointmentApiProvider = appointmentApiProvider;
  }

  @Override
  public AppointmentRepository get() {
    return provideAppointmentRepository(module, appointmentDaoProvider.get(), appointmentApiProvider.get());
  }

  public static RepositoryModule_ProvideAppointmentRepositoryFactory create(RepositoryModule module,
      Provider<AppointmentDao> appointmentDaoProvider,
      Provider<AppointmentApi> appointmentApiProvider) {
    return new RepositoryModule_ProvideAppointmentRepositoryFactory(module, appointmentDaoProvider, appointmentApiProvider);
  }

  public static AppointmentRepository provideAppointmentRepository(RepositoryModule instance,
      AppointmentDao appointmentDao, AppointmentApi appointmentApi) {
    return Preconditions.checkNotNullFromProvides(instance.provideAppointmentRepository(appointmentDao, appointmentApi));
  }
}
