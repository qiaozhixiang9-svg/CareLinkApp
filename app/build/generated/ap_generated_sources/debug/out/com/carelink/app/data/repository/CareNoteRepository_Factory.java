package com.carelink.app.data.repository;

import com.carelink.app.data.local.dao.CareNoteDao;
import com.carelink.app.data.remote.api.NoteApi;
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
public final class CareNoteRepository_Factory implements Factory<CareNoteRepository> {
  private final Provider<CareNoteDao> careNoteDaoProvider;

  private final Provider<NoteApi> noteApiProvider;

  public CareNoteRepository_Factory(Provider<CareNoteDao> careNoteDaoProvider,
      Provider<NoteApi> noteApiProvider) {
    this.careNoteDaoProvider = careNoteDaoProvider;
    this.noteApiProvider = noteApiProvider;
  }

  @Override
  public CareNoteRepository get() {
    return newInstance(careNoteDaoProvider.get(), noteApiProvider.get());
  }

  public static CareNoteRepository_Factory create(Provider<CareNoteDao> careNoteDaoProvider,
      Provider<NoteApi> noteApiProvider) {
    return new CareNoteRepository_Factory(careNoteDaoProvider, noteApiProvider);
  }

  public static CareNoteRepository newInstance(CareNoteDao careNoteDao, NoteApi noteApi) {
    return new CareNoteRepository(careNoteDao, noteApi);
  }
}
