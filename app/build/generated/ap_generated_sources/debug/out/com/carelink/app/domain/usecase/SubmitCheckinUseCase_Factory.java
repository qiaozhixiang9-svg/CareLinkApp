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
public final class SubmitCheckinUseCase_Factory implements Factory<SubmitCheckinUseCase> {
  private final Provider<CheckinRepository> checkinRepositoryProvider;

  public SubmitCheckinUseCase_Factory(Provider<CheckinRepository> checkinRepositoryProvider) {
    this.checkinRepositoryProvider = checkinRepositoryProvider;
  }

  @Override
  public SubmitCheckinUseCase get() {
    return newInstance(checkinRepositoryProvider.get());
  }

  public static SubmitCheckinUseCase_Factory create(
      Provider<CheckinRepository> checkinRepositoryProvider) {
    return new SubmitCheckinUseCase_Factory(checkinRepositoryProvider);
  }

  public static SubmitCheckinUseCase newInstance(CheckinRepository checkinRepository) {
    return new SubmitCheckinUseCase(checkinRepository);
  }
}
