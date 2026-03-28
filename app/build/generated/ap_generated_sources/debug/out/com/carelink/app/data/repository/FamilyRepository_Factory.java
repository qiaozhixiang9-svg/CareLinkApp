package com.carelink.app.data.repository;

import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.data.remote.api.FamilyApi;
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
public final class FamilyRepository_Factory implements Factory<FamilyRepository> {
  private final Provider<FamilyApi> familyApiProvider;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  public FamilyRepository_Factory(Provider<FamilyApi> familyApiProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    this.familyApiProvider = familyApiProvider;
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  @Override
  public FamilyRepository get() {
    return newInstance(familyApiProvider.get(), preferenceManagerProvider.get());
  }

  public static FamilyRepository_Factory create(Provider<FamilyApi> familyApiProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new FamilyRepository_Factory(familyApiProvider, preferenceManagerProvider);
  }

  public static FamilyRepository newInstance(FamilyApi familyApi,
      PreferenceManager preferenceManager) {
    return new FamilyRepository(familyApi, preferenceManager);
  }
}
