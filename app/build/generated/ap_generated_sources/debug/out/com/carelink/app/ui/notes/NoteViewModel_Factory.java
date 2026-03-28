package com.carelink.app.ui.notes;

import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.data.repository.CareNoteRepository;
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
public final class NoteViewModel_Factory implements Factory<NoteViewModel> {
  private final Provider<CareNoteRepository> careNoteRepositoryProvider;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  public NoteViewModel_Factory(Provider<CareNoteRepository> careNoteRepositoryProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    this.careNoteRepositoryProvider = careNoteRepositoryProvider;
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  @Override
  public NoteViewModel get() {
    return newInstance(careNoteRepositoryProvider.get(), preferenceManagerProvider.get());
  }

  public static NoteViewModel_Factory create(
      Provider<CareNoteRepository> careNoteRepositoryProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new NoteViewModel_Factory(careNoteRepositoryProvider, preferenceManagerProvider);
  }

  public static NoteViewModel newInstance(CareNoteRepository careNoteRepository,
      PreferenceManager preferenceManager) {
    return new NoteViewModel(careNoteRepository, preferenceManager);
  }
}
