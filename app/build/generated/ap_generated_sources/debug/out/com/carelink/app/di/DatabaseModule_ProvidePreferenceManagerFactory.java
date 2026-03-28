package com.carelink.app.di;

import android.content.Context;
import com.carelink.app.data.local.pref.PreferenceManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DatabaseModule_ProvidePreferenceManagerFactory implements Factory<PreferenceManager> {
  private final DatabaseModule module;

  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvidePreferenceManagerFactory(DatabaseModule module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public PreferenceManager get() {
    return providePreferenceManager(module, contextProvider.get());
  }

  public static DatabaseModule_ProvidePreferenceManagerFactory create(DatabaseModule module,
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvidePreferenceManagerFactory(module, contextProvider);
  }

  public static PreferenceManager providePreferenceManager(DatabaseModule instance,
      Context context) {
    return Preconditions.checkNotNullFromProvides(instance.providePreferenceManager(context));
  }
}
