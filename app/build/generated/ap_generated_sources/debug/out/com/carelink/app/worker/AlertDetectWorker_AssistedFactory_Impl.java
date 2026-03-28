package com.carelink.app.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class AlertDetectWorker_AssistedFactory_Impl implements AlertDetectWorker_AssistedFactory {
  private final AlertDetectWorker_Factory delegateFactory;

  AlertDetectWorker_AssistedFactory_Impl(AlertDetectWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public AlertDetectWorker create(Context arg0, WorkerParameters arg1) {
    return delegateFactory.get(arg0, arg1);
  }

  public static Provider<AlertDetectWorker_AssistedFactory> create(
      AlertDetectWorker_Factory delegateFactory) {
    return InstanceFactory.create(new AlertDetectWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<AlertDetectWorker_AssistedFactory> createFactoryProvider(
      AlertDetectWorker_Factory delegateFactory) {
    return InstanceFactory.create(new AlertDetectWorker_AssistedFactory_Impl(delegateFactory));
  }
}
