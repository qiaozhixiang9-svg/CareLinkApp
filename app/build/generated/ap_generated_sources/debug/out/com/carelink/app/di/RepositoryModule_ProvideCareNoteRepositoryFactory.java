package com.carelink.app.di;

import com.carelink.app.data.local.dao.CareNoteDao;
import com.carelink.app.data.remote.api.NoteApi;
import com.carelink.app.data.repository.CareNoteRepository;
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
public final class RepositoryModule_ProvideCareNoteRepositoryFactory implements Factory<CareNoteRepository> {
  private final RepositoryModule module;

  private final Provider<CareNoteDao> careNoteDaoProvider;

  private final Provider<NoteApi> noteApiProvider;

  public RepositoryModule_ProvideCareNoteRepositoryFactory(RepositoryModule module,
      Provider<CareNoteDao> careNoteDaoProvider, Provider<NoteApi> noteApiProvider) {
    this.module = module;
    this.careNoteDaoProvider = careNoteDaoProvider;
    this.noteApiProvider = noteApiProvider;
  }

  @Override
  public CareNoteRepository get() {
    return provideCareNoteRepository(module, careNoteDaoProvider.get(), noteApiProvider.get());
  }

  public static RepositoryModule_ProvideCareNoteRepositoryFactory create(RepositoryModule module,
      Provider<CareNoteDao> careNoteDaoProvider, Provider<NoteApi> noteApiProvider) {
    return new RepositoryModule_ProvideCareNoteRepositoryFactory(module, careNoteDaoProvider, noteApiProvider);
  }

  public static CareNoteRepository provideCareNoteRepository(RepositoryModule instance,
      CareNoteDao careNoteDao, NoteApi noteApi) {
    return Preconditions.checkNotNullFromProvides(instance.provideCareNoteRepository(careNoteDao, noteApi));
  }
}
