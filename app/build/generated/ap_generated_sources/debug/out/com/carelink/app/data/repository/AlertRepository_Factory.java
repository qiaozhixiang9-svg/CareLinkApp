package com.carelink.app.data.repository;

import com.carelink.app.data.local.dao.AlertDao;
import com.carelink.app.data.remote.api.AlertApi;
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
public final class AlertRepository_Factory implements Factory<AlertRepository> {
  private final Provider<AlertDao> alertDaoProvider;

  private final Provider<AlertApi> alertApiProvider;

  public AlertRepository_Factory(Provider<AlertDao> alertDaoProvider,
      Provider<AlertApi> alertApiProvider) {
    this.alertDaoProvider = alertDaoProvider;
    this.alertApiProvider = alertApiProvider;
  }

  @Override
  public AlertRepository get() {
    return newInstance(alertDaoProvider.get(), alertApiProvider.get());
  }

  public static AlertRepository_Factory create(Provider<AlertDao> alertDaoProvider,
      Provider<AlertApi> alertApiProvider) {
    return new AlertRepository_Factory(alertDaoProvider, alertApiProvider);
  }

  public static AlertRepository newInstance(AlertDao alertDao, AlertApi alertApi) {
    return new AlertRepository(alertDao, alertApi);
  }
}
