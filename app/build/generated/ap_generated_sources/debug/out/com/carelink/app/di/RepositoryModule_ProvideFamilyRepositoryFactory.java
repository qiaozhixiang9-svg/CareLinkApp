package com.carelink.app.di;

import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.data.remote.api.FamilyApi;
import com.carelink.app.data.repository.FamilyRepository;
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
public final class RepositoryModule_ProvideFamilyRepositoryFactory implements Factory<FamilyRepository> {
  private final RepositoryModule module;

  private final Provider<FamilyApi> familyApiProvider;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  public RepositoryModule_ProvideFamilyRepositoryFactory(RepositoryModule module,
      Provider<FamilyApi> familyApiProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    this.module = module;
    this.familyApiProvider = familyApiProvider;
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  @Override
  public FamilyRepository get() {
    return provideFamilyRepository(module, familyApiProvider.get(), preferenceManagerProvider.get());
  }

  public static RepositoryModule_ProvideFamilyRepositoryFactory create(RepositoryModule module,
      Provider<FamilyApi> familyApiProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new RepositoryModule_ProvideFamilyRepositoryFactory(module, familyApiProvider, preferenceManagerProvider);
  }

  public static FamilyRepository provideFamilyRepository(RepositoryModule instance,
      FamilyApi familyApi, PreferenceManager preferenceManager) {
    return Preconditions.checkNotNullFromProvides(instance.provideFamilyRepository(familyApi, preferenceManager));
  }
}
