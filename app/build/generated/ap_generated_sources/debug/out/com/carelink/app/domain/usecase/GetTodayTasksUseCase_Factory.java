package com.carelink.app.domain.usecase;

import com.carelink.app.data.repository.CheckinRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class GetTodayTasksUseCase_Factory implements Factory<GetTodayTasksUseCase> {
  private final Provider<CheckinRepository> checkinRepositoryProvider;

  public GetTodayTasksUseCase_Factory(Provider<CheckinRepository> checkinRepositoryProvider) {
    this.checkinRepositoryProvider = checkinRepositoryProvider;
  }

  @Override
  public GetTodayTasksUseCase get() {
    return newInstance(checkinRepositoryProvider.get());
  }

  public static GetTodayTasksUseCase_Factory create(
      Provider<CheckinRepository> checkinRepositoryProvider) {
    return new GetTodayTasksUseCase_Factory(checkinRepositoryProvider);
  }

  public static GetTodayTasksUseCase newInstance(CheckinRepository checkinRepository) {
    return new GetTodayTasksUseCase(checkinRepository);
  }
}
