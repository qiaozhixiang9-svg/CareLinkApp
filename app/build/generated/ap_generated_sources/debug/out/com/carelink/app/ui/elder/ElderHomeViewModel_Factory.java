package com.carelink.app.ui.elder;

import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.domain.usecase.GetTodayTasksUseCase;
import com.carelink.app.domain.usecase.SubmitCheckinUseCase;
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
public final class ElderHomeViewModel_Factory implements Factory<ElderHomeViewModel> {
  private final Provider<GetTodayTasksUseCase> getTodayTasksUseCaseProvider;

  private final Provider<SubmitCheckinUseCase> submitCheckinUseCaseProvider;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  public ElderHomeViewModel_Factory(Provider<GetTodayTasksUseCase> getTodayTasksUseCaseProvider,
      Provider<SubmitCheckinUseCase> submitCheckinUseCaseProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    this.getTodayTasksUseCaseProvider = getTodayTasksUseCaseProvider;
    this.submitCheckinUseCaseProvider = submitCheckinUseCaseProvider;
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  @Override
  public ElderHomeViewModel get() {
    return newInstance(getTodayTasksUseCaseProvider.get(), submitCheckinUseCaseProvider.get(), preferenceManagerProvider.get());
  }

  public static ElderHomeViewModel_Factory create(
      Provider<GetTodayTasksUseCase> getTodayTasksUseCaseProvider,
      Provider<SubmitCheckinUseCase> submitCheckinUseCaseProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new ElderHomeViewModel_Factory(getTodayTasksUseCaseProvider, submitCheckinUseCaseProvider, preferenceManagerProvider);
  }

  public static ElderHomeViewModel newInstance(GetTodayTasksUseCase getTodayTasksUseCase,
      SubmitCheckinUseCase submitCheckinUseCase, PreferenceManager preferenceManager) {
    return new ElderHomeViewModel(getTodayTasksUseCase, submitCheckinUseCase, preferenceManager);
  }
}
