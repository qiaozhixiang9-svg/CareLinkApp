package com.carelink.app.di;

import com.carelink.app.data.local.dao.ShiftAssignmentDao;
import com.carelink.app.data.remote.api.ShiftApi;
import com.carelink.app.data.repository.ShiftRepository;
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
public final class RepositoryModule_ProvideShiftRepositoryFactory implements Factory<ShiftRepository> {
  private final RepositoryModule module;

  private final Provider<ShiftAssignmentDao> shiftDaoProvider;

  private final Provider<ShiftApi> shiftApiProvider;

  public RepositoryModule_ProvideShiftRepositoryFactory(RepositoryModule module,
      Provider<ShiftAssignmentDao> shiftDaoProvider, Provider<ShiftApi> shiftApiProvider) {
    this.module = module;
    this.shiftDaoProvider = shiftDaoProvider;
    this.shiftApiProvider = shiftApiProvider;
  }

  @Override
  public ShiftRepository get() {
    return provideShiftRepository(module, shiftDaoProvider.get(), shiftApiProvider.get());
  }

  public static RepositoryModule_ProvideShiftRepositoryFactory create(RepositoryModule module,
      Provider<ShiftAssignmentDao> shiftDaoProvider, Provider<ShiftApi> shiftApiProvider) {
    return new RepositoryModule_ProvideShiftRepositoryFactory(module, shiftDaoProvider, shiftApiProvider);
  }

  public static ShiftRepository provideShiftRepository(RepositoryModule instance,
      ShiftAssignmentDao shiftDao, ShiftApi shiftApi) {
    return Preconditions.checkNotNullFromProvides(instance.provideShiftRepository(shiftDao, shiftApi));
  }
}
