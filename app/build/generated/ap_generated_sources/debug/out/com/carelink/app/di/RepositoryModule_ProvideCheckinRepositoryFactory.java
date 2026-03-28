package com.carelink.app.di;

import com.carelink.app.data.local.dao.CheckinDao;
import com.carelink.app.data.local.dao.CheckinTaskDao;
import com.carelink.app.data.remote.api.CheckinApi;
import com.carelink.app.data.repository.CheckinRepository;
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
public final class RepositoryModule_ProvideCheckinRepositoryFactory implements Factory<CheckinRepository> {
  private final RepositoryModule module;

  private final Provider<CheckinDao> checkinDaoProvider;

  private final Provider<CheckinTaskDao> taskDaoProvider;

  private final Provider<CheckinApi> checkinApiProvider;

  public RepositoryModule_ProvideCheckinRepositoryFactory(RepositoryModule module,
      Provider<CheckinDao> checkinDaoProvider, Provider<CheckinTaskDao> taskDaoProvider,
      Provider<CheckinApi> checkinApiProvider) {
    this.module = module;
    this.checkinDaoProvider = checkinDaoProvider;
    this.taskDaoProvider = taskDaoProvider;
    this.checkinApiProvider = checkinApiProvider;
  }

  @Override
  public CheckinRepository get() {
    return provideCheckinRepository(module, checkinDaoProvider.get(), taskDaoProvider.get(), checkinApiProvider.get());
  }

  public static RepositoryModule_ProvideCheckinRepositoryFactory create(RepositoryModule module,
      Provider<CheckinDao> checkinDaoProvider, Provider<CheckinTaskDao> taskDaoProvider,
      Provider<CheckinApi> checkinApiProvider) {
    return new RepositoryModule_ProvideCheckinRepositoryFactory(module, checkinDaoProvider, taskDaoProvider, checkinApiProvider);
  }

  public static CheckinRepository provideCheckinRepository(RepositoryModule instance,
      CheckinDao checkinDao, CheckinTaskDao taskDao, CheckinApi checkinApi) {
    return Preconditions.checkNotNullFromProvides(instance.provideCheckinRepository(checkinDao, taskDao, checkinApi));
  }
}
