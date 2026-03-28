package com.carelink.app.data.repository;

import com.carelink.app.data.local.dao.CheckinDao;
import com.carelink.app.data.local.dao.CheckinTaskDao;
import com.carelink.app.data.remote.api.CheckinApi;
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
public final class CheckinRepository_Factory implements Factory<CheckinRepository> {
  private final Provider<CheckinDao> checkinDaoProvider;

  private final Provider<CheckinTaskDao> taskDaoProvider;

  private final Provider<CheckinApi> checkinApiProvider;

  public CheckinRepository_Factory(Provider<CheckinDao> checkinDaoProvider,
      Provider<CheckinTaskDao> taskDaoProvider, Provider<CheckinApi> checkinApiProvider) {
    this.checkinDaoProvider = checkinDaoProvider;
    this.taskDaoProvider = taskDaoProvider;
    this.checkinApiProvider = checkinApiProvider;
  }

  @Override
  public CheckinRepository get() {
    return newInstance(checkinDaoProvider.get(), taskDaoProvider.get(), checkinApiProvider.get());
  }

  public static CheckinRepository_Factory create(Provider<CheckinDao> checkinDaoProvider,
      Provider<CheckinTaskDao> taskDaoProvider, Provider<CheckinApi> checkinApiProvider) {
    return new CheckinRepository_Factory(checkinDaoProvider, taskDaoProvider, checkinApiProvider);
  }

  public static CheckinRepository newInstance(CheckinDao checkinDao, CheckinTaskDao taskDao,
      CheckinApi checkinApi) {
    return new CheckinRepository(checkinDao, taskDao, checkinApi);
  }
}
