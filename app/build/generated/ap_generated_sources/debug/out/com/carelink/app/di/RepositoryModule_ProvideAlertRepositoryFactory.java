package com.carelink.app.di;

import com.carelink.app.data.local.dao.AlertDao;
import com.carelink.app.data.remote.api.AlertApi;
import com.carelink.app.data.repository.AlertRepository;
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
public final class RepositoryModule_ProvideAlertRepositoryFactory implements Factory<AlertRepository> {
  private final RepositoryModule module;

  private final Provider<AlertDao> alertDaoProvider;

  private final Provider<AlertApi> alertApiProvider;

  public RepositoryModule_ProvideAlertRepositoryFactory(RepositoryModule module,
      Provider<AlertDao> alertDaoProvider, Provider<AlertApi> alertApiProvider) {
    this.module = module;
    this.alertDaoProvider = alertDaoProvider;
    this.alertApiProvider = alertApiProvider;
  }

  @Override
  public AlertRepository get() {
    return provideAlertRepository(module, alertDaoProvider.get(), alertApiProvider.get());
  }

  public static RepositoryModule_ProvideAlertRepositoryFactory create(RepositoryModule module,
      Provider<AlertDao> alertDaoProvider, Provider<AlertApi> alertApiProvider) {
    return new RepositoryModule_ProvideAlertRepositoryFactory(module, alertDaoProvider, alertApiProvider);
  }

  public static AlertRepository provideAlertRepository(RepositoryModule instance, AlertDao alertDao,
      AlertApi alertApi) {
    return Preconditions.checkNotNullFromProvides(instance.provideAlertRepository(alertDao, alertApi));
  }
}
