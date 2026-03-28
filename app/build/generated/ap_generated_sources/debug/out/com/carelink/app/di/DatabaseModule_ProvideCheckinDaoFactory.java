package com.carelink.app.di;

import com.carelink.app.data.local.dao.CheckinDao;
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
public final class DatabaseModule_ProvideCheckinDaoFactory implements Factory<CheckinDao> {
  private final DatabaseModule module;

  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideCheckinDaoFactory(DatabaseModule module,
      Provider<AppDatabase> dbProvider) {
    this.module = module;
    this.dbProvider = dbProvider;
  }

  @Override
  public CheckinDao get() {
    return provideCheckinDao(module, dbProvider.get());
  }

  public static DatabaseModule_ProvideCheckinDaoFactory create(DatabaseModule module,
      Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideCheckinDaoFactory(module, dbProvider);
  }

  public static CheckinDao provideCheckinDao(DatabaseModule instance, AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(instance.provideCheckinDao(db));
  }
}
