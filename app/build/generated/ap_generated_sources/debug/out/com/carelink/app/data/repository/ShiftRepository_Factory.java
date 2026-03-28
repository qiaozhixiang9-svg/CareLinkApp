package com.carelink.app.data.repository;

import com.carelink.app.data.local.dao.ShiftAssignmentDao;
import com.carelink.app.data.remote.api.ShiftApi;
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
public final class ShiftRepository_Factory implements Factory<ShiftRepository> {
  private final Provider<ShiftAssignmentDao> shiftDaoProvider;

  private final Provider<ShiftApi> shiftApiProvider;

  public ShiftRepository_Factory(Provider<ShiftAssignmentDao> shiftDaoProvider,
      Provider<ShiftApi> shiftApiProvider) {
    this.shiftDaoProvider = shiftDaoProvider;
    this.shiftApiProvider = shiftApiProvider;
  }

  @Override
  public ShiftRepository get() {
    return newInstance(shiftDaoProvider.get(), shiftApiProvider.get());
  }

  public static ShiftRepository_Factory create(Provider<ShiftAssignmentDao> shiftDaoProvider,
      Provider<ShiftApi> shiftApiProvider) {
    return new ShiftRepository_Factory(shiftDaoProvider, shiftApiProvider);
  }

  public static ShiftRepository newInstance(ShiftAssignmentDao shiftDao, ShiftApi shiftApi) {
    return new ShiftRepository(shiftDao, shiftApi);
  }
}
