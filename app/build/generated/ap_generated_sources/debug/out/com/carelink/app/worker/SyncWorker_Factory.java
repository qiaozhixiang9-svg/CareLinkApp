package com.carelink.app.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class SyncWorker_Factory {
  public SyncWorker_Factory() {
  }

  public SyncWorker get(Context context, WorkerParameters params) {
    return newInstance(context, params);
  }

  public static SyncWorker_Factory create() {
    return new SyncWorker_Factory();
  }

  public static SyncWorker newInstance(Context context, WorkerParameters params) {
    return new SyncWorker(context, params);
  }
}
