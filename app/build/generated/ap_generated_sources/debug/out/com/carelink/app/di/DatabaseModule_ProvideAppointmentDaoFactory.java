package com.carelink.app.di;

import com.carelink.app.data.local.dao.AppointmentDao;
import com.carelink.app.data.local.db.AppDatabase;
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
public final class DatabaseModule_ProvideAppointmentDaoFactory implements Factory<AppointmentDao> {
  private final DatabaseModule module;

  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideAppointmentDaoFactory(DatabaseModule module,
      Provider<AppDatabase> dbProvider) {
    this.module = module;
    this.dbProvider = dbProvider;
  }

  @Override
  public AppointmentDao get() {
    return provideAppointmentDao(module, dbProvider.get());
  }

  public static DatabaseModule_ProvideAppointmentDaoFactory create(DatabaseModule module,
      Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideAppointmentDaoFactory(module, dbProvider);
  }

  public static AppointmentDao provideAppointmentDao(DatabaseModule instance, AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(instance.provideAppointmentDao(db));
  }
}
