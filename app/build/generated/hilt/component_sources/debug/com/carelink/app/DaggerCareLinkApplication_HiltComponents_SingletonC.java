package com.carelink.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.carelink.app.data.local.dao.AlertDao;
import com.carelink.app.data.local.dao.AppointmentDao;
import com.carelink.app.data.local.dao.CareNoteDao;
import com.carelink.app.data.local.dao.CheckinDao;
import com.carelink.app.data.local.dao.CheckinTaskDao;
import com.carelink.app.data.local.db.AppDatabase;
import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.data.remote.api.AlertApi;
import com.carelink.app.data.remote.api.AppointmentApi;
import com.carelink.app.data.remote.api.CheckinApi;
import com.carelink.app.data.remote.api.NoteApi;
import com.carelink.app.data.remote.service.AuthInterceptor;
import com.carelink.app.data.repository.AlertRepository;
import com.carelink.app.data.repository.AppointmentRepository;
import com.carelink.app.data.repository.CareNoteRepository;
import com.carelink.app.data.repository.CheckinRepository;
import com.carelink.app.di.DatabaseModule;
import com.carelink.app.di.DatabaseModule_ProvideAlertDaoFactory;
import com.carelink.app.di.DatabaseModule_ProvideAppointmentDaoFactory;
import com.carelink.app.di.DatabaseModule_ProvideCareNoteDaoFactory;
import com.carelink.app.di.DatabaseModule_ProvideCheckinDaoFactory;
import com.carelink.app.di.DatabaseModule_ProvideCheckinTaskDaoFactory;
import com.carelink.app.di.DatabaseModule_ProvideDatabaseFactory;
import com.carelink.app.di.DatabaseModule_ProvidePreferenceManagerFactory;
import com.carelink.app.di.NetworkModule;
import com.carelink.app.di.NetworkModule_ProvideAlertApiFactory;
import com.carelink.app.di.NetworkModule_ProvideAppointmentApiFactory;
import com.carelink.app.di.NetworkModule_ProvideAuthInterceptorFactory;
import com.carelink.app.di.NetworkModule_ProvideCheckinApiFactory;
import com.carelink.app.di.NetworkModule_ProvideLoggingInterceptorFactory;
import com.carelink.app.di.NetworkModule_ProvideNoteApiFactory;
import com.carelink.app.di.NetworkModule_ProvideOkHttpClientFactory;
import com.carelink.app.di.NetworkModule_ProvideRetrofitFactory;
import com.carelink.app.di.RepositoryModule;
import com.carelink.app.di.RepositoryModule_ProvideAlertRepositoryFactory;
import com.carelink.app.di.RepositoryModule_ProvideAppointmentRepositoryFactory;
import com.carelink.app.di.RepositoryModule_ProvideCareNoteRepositoryFactory;
import com.carelink.app.di.RepositoryModule_ProvideCheckinRepositoryFactory;
import com.carelink.app.domain.usecase.GetTodayTasksUseCase;
import com.carelink.app.domain.usecase.SubmitCheckinUseCase;
import com.carelink.app.ui.alert.AlertViewModel;
import com.carelink.app.ui.alert.AlertViewModel_HiltModules;
import com.carelink.app.ui.auth.LoginViewModel;
import com.carelink.app.ui.auth.LoginViewModel_HiltModules;
import com.carelink.app.ui.calendar.AppointmentEditActivity;
import com.carelink.app.ui.calendar.AppointmentViewModel;
import com.carelink.app.ui.calendar.AppointmentViewModel_HiltModules;
import com.carelink.app.ui.elder.ElderHomeViewModel;
import com.carelink.app.ui.elder.ElderHomeViewModel_HiltModules;
import com.carelink.app.ui.notes.NoteViewModel;
import com.carelink.app.ui.notes.NoteViewModel_HiltModules;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

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
public final class DaggerCareLinkApplication_HiltComponents_SingletonC {
  private DaggerCareLinkApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private DatabaseModule databaseModule;

    private NetworkModule networkModule;

    private RepositoryModule repositoryModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public Builder databaseModule(DatabaseModule databaseModule) {
      this.databaseModule = Preconditions.checkNotNull(databaseModule);
      return this;
    }

    public Builder networkModule(NetworkModule networkModule) {
      this.networkModule = Preconditions.checkNotNull(networkModule);
      return this;
    }

    public Builder repositoryModule(RepositoryModule repositoryModule) {
      this.repositoryModule = Preconditions.checkNotNull(repositoryModule);
      return this;
    }

    public CareLinkApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      if (databaseModule == null) {
        this.databaseModule = new DatabaseModule();
      }
      if (networkModule == null) {
        this.networkModule = new NetworkModule();
      }
      if (repositoryModule == null) {
        this.repositoryModule = new RepositoryModule();
      }
      return new SingletonCImpl(applicationContextModule, databaseModule, networkModule, repositoryModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements CareLinkApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public CareLinkApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements CareLinkApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public CareLinkApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements CareLinkApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public CareLinkApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements CareLinkApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public CareLinkApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements CareLinkApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public CareLinkApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements CareLinkApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public CareLinkApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements CareLinkApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public CareLinkApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends CareLinkApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends CareLinkApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends CareLinkApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends CareLinkApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectAppointmentEditActivity(AppointmentEditActivity appointmentEditActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(5).put(LazyClassKeyProvider.com_carelink_app_ui_alert_AlertViewModel, AlertViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_carelink_app_ui_calendar_AppointmentViewModel, AppointmentViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_carelink_app_ui_elder_ElderHomeViewModel, ElderHomeViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_carelink_app_ui_auth_LoginViewModel, LoginViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_carelink_app_ui_notes_NoteViewModel, NoteViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_carelink_app_ui_notes_NoteViewModel = "com.carelink.app.ui.notes.NoteViewModel";

      static String com_carelink_app_ui_auth_LoginViewModel = "com.carelink.app.ui.auth.LoginViewModel";

      static String com_carelink_app_ui_calendar_AppointmentViewModel = "com.carelink.app.ui.calendar.AppointmentViewModel";

      static String com_carelink_app_ui_elder_ElderHomeViewModel = "com.carelink.app.ui.elder.ElderHomeViewModel";

      static String com_carelink_app_ui_alert_AlertViewModel = "com.carelink.app.ui.alert.AlertViewModel";

      @KeepFieldType
      NoteViewModel com_carelink_app_ui_notes_NoteViewModel2;

      @KeepFieldType
      LoginViewModel com_carelink_app_ui_auth_LoginViewModel2;

      @KeepFieldType
      AppointmentViewModel com_carelink_app_ui_calendar_AppointmentViewModel2;

      @KeepFieldType
      ElderHomeViewModel com_carelink_app_ui_elder_ElderHomeViewModel2;

      @KeepFieldType
      AlertViewModel com_carelink_app_ui_alert_AlertViewModel2;
    }
  }

  private static final class ViewModelCImpl extends CareLinkApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AlertViewModel> alertViewModelProvider;

    private Provider<AppointmentViewModel> appointmentViewModelProvider;

    private Provider<ElderHomeViewModel> elderHomeViewModelProvider;

    private Provider<LoginViewModel> loginViewModelProvider;

    private Provider<NoteViewModel> noteViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private GetTodayTasksUseCase getTodayTasksUseCase() {
      return new GetTodayTasksUseCase(singletonCImpl.provideCheckinRepositoryProvider.get());
    }

    private SubmitCheckinUseCase submitCheckinUseCase() {
      return new SubmitCheckinUseCase(singletonCImpl.provideCheckinRepositoryProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.alertViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.appointmentViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.elderHomeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.loginViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.noteViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(5).put(LazyClassKeyProvider.com_carelink_app_ui_alert_AlertViewModel, ((Provider) alertViewModelProvider)).put(LazyClassKeyProvider.com_carelink_app_ui_calendar_AppointmentViewModel, ((Provider) appointmentViewModelProvider)).put(LazyClassKeyProvider.com_carelink_app_ui_elder_ElderHomeViewModel, ((Provider) elderHomeViewModelProvider)).put(LazyClassKeyProvider.com_carelink_app_ui_auth_LoginViewModel, ((Provider) loginViewModelProvider)).put(LazyClassKeyProvider.com_carelink_app_ui_notes_NoteViewModel, ((Provider) noteViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_carelink_app_ui_calendar_AppointmentViewModel = "com.carelink.app.ui.calendar.AppointmentViewModel";

      static String com_carelink_app_ui_elder_ElderHomeViewModel = "com.carelink.app.ui.elder.ElderHomeViewModel";

      static String com_carelink_app_ui_alert_AlertViewModel = "com.carelink.app.ui.alert.AlertViewModel";

      static String com_carelink_app_ui_auth_LoginViewModel = "com.carelink.app.ui.auth.LoginViewModel";

      static String com_carelink_app_ui_notes_NoteViewModel = "com.carelink.app.ui.notes.NoteViewModel";

      @KeepFieldType
      AppointmentViewModel com_carelink_app_ui_calendar_AppointmentViewModel2;

      @KeepFieldType
      ElderHomeViewModel com_carelink_app_ui_elder_ElderHomeViewModel2;

      @KeepFieldType
      AlertViewModel com_carelink_app_ui_alert_AlertViewModel2;

      @KeepFieldType
      LoginViewModel com_carelink_app_ui_auth_LoginViewModel2;

      @KeepFieldType
      NoteViewModel com_carelink_app_ui_notes_NoteViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.carelink.app.ui.alert.AlertViewModel 
          return (T) new AlertViewModel(singletonCImpl.provideAlertRepositoryProvider.get(), singletonCImpl.providePreferenceManagerProvider.get());

          case 1: // com.carelink.app.ui.calendar.AppointmentViewModel 
          return (T) new AppointmentViewModel(singletonCImpl.provideAppointmentRepositoryProvider.get(), singletonCImpl.providePreferenceManagerProvider.get());

          case 2: // com.carelink.app.ui.elder.ElderHomeViewModel 
          return (T) new ElderHomeViewModel(viewModelCImpl.getTodayTasksUseCase(), viewModelCImpl.submitCheckinUseCase(), singletonCImpl.providePreferenceManagerProvider.get());

          case 3: // com.carelink.app.ui.auth.LoginViewModel 
          return (T) new LoginViewModel(singletonCImpl.providePreferenceManagerProvider.get());

          case 4: // com.carelink.app.ui.notes.NoteViewModel 
          return (T) new NoteViewModel(singletonCImpl.provideCareNoteRepositoryProvider.get(), singletonCImpl.providePreferenceManagerProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends CareLinkApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends CareLinkApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends CareLinkApplication_HiltComponents.SingletonC {
    private final RepositoryModule repositoryModule;

    private final DatabaseModule databaseModule;

    private final ApplicationContextModule applicationContextModule;

    private final NetworkModule networkModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<AppDatabase> provideDatabaseProvider;

    private Provider<AlertDao> provideAlertDaoProvider;

    private Provider<HttpLoggingInterceptor> provideLoggingInterceptorProvider;

    private Provider<PreferenceManager> providePreferenceManagerProvider;

    private Provider<AuthInterceptor> provideAuthInterceptorProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<AlertApi> provideAlertApiProvider;

    private Provider<AlertRepository> provideAlertRepositoryProvider;

    private Provider<AppointmentDao> provideAppointmentDaoProvider;

    private Provider<AppointmentApi> provideAppointmentApiProvider;

    private Provider<AppointmentRepository> provideAppointmentRepositoryProvider;

    private Provider<CheckinDao> provideCheckinDaoProvider;

    private Provider<CheckinTaskDao> provideCheckinTaskDaoProvider;

    private Provider<CheckinApi> provideCheckinApiProvider;

    private Provider<CheckinRepository> provideCheckinRepositoryProvider;

    private Provider<CareNoteDao> provideCareNoteDaoProvider;

    private Provider<NoteApi> provideNoteApiProvider;

    private Provider<CareNoteRepository> provideCareNoteRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam,
        DatabaseModule databaseModuleParam, NetworkModule networkModuleParam,
        RepositoryModule repositoryModuleParam) {
      this.repositoryModule = repositoryModuleParam;
      this.databaseModule = databaseModuleParam;
      this.applicationContextModule = applicationContextModuleParam;
      this.networkModule = networkModuleParam;
      initialize(applicationContextModuleParam, databaseModuleParam, networkModuleParam, repositoryModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam,
        final DatabaseModule databaseModuleParam, final NetworkModule networkModuleParam,
        final RepositoryModule repositoryModuleParam) {
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 2));
      this.provideAlertDaoProvider = DoubleCheck.provider(new SwitchingProvider<AlertDao>(singletonCImpl, 1));
      this.provideLoggingInterceptorProvider = DoubleCheck.provider(new SwitchingProvider<HttpLoggingInterceptor>(singletonCImpl, 6));
      this.providePreferenceManagerProvider = DoubleCheck.provider(new SwitchingProvider<PreferenceManager>(singletonCImpl, 8));
      this.provideAuthInterceptorProvider = DoubleCheck.provider(new SwitchingProvider<AuthInterceptor>(singletonCImpl, 7));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 5));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 4));
      this.provideAlertApiProvider = DoubleCheck.provider(new SwitchingProvider<AlertApi>(singletonCImpl, 3));
      this.provideAlertRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AlertRepository>(singletonCImpl, 0));
      this.provideAppointmentDaoProvider = DoubleCheck.provider(new SwitchingProvider<AppointmentDao>(singletonCImpl, 10));
      this.provideAppointmentApiProvider = DoubleCheck.provider(new SwitchingProvider<AppointmentApi>(singletonCImpl, 11));
      this.provideAppointmentRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AppointmentRepository>(singletonCImpl, 9));
      this.provideCheckinDaoProvider = DoubleCheck.provider(new SwitchingProvider<CheckinDao>(singletonCImpl, 13));
      this.provideCheckinTaskDaoProvider = DoubleCheck.provider(new SwitchingProvider<CheckinTaskDao>(singletonCImpl, 14));
      this.provideCheckinApiProvider = DoubleCheck.provider(new SwitchingProvider<CheckinApi>(singletonCImpl, 15));
      this.provideCheckinRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<CheckinRepository>(singletonCImpl, 12));
      this.provideCareNoteDaoProvider = DoubleCheck.provider(new SwitchingProvider<CareNoteDao>(singletonCImpl, 17));
      this.provideNoteApiProvider = DoubleCheck.provider(new SwitchingProvider<NoteApi>(singletonCImpl, 18));
      this.provideCareNoteRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<CareNoteRepository>(singletonCImpl, 16));
    }

    @Override
    public void injectCareLinkApplication(CareLinkApplication careLinkApplication) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.carelink.app.data.repository.AlertRepository 
          return (T) RepositoryModule_ProvideAlertRepositoryFactory.provideAlertRepository(singletonCImpl.repositoryModule, singletonCImpl.provideAlertDaoProvider.get(), singletonCImpl.provideAlertApiProvider.get());

          case 1: // com.carelink.app.data.local.dao.AlertDao 
          return (T) DatabaseModule_ProvideAlertDaoFactory.provideAlertDao(singletonCImpl.databaseModule, singletonCImpl.provideDatabaseProvider.get());

          case 2: // com.carelink.app.data.local.db.AppDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(singletonCImpl.databaseModule, ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.carelink.app.data.remote.api.AlertApi 
          return (T) NetworkModule_ProvideAlertApiFactory.provideAlertApi(singletonCImpl.networkModule, singletonCImpl.provideRetrofitProvider.get());

          case 4: // retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.networkModule, singletonCImpl.provideOkHttpClientProvider.get());

          case 5: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient(singletonCImpl.networkModule, singletonCImpl.provideLoggingInterceptorProvider.get(), singletonCImpl.provideAuthInterceptorProvider.get());

          case 6: // okhttp3.logging.HttpLoggingInterceptor 
          return (T) NetworkModule_ProvideLoggingInterceptorFactory.provideLoggingInterceptor(singletonCImpl.networkModule);

          case 7: // com.carelink.app.data.remote.service.AuthInterceptor 
          return (T) NetworkModule_ProvideAuthInterceptorFactory.provideAuthInterceptor(singletonCImpl.networkModule, singletonCImpl.providePreferenceManagerProvider.get());

          case 8: // com.carelink.app.data.local.pref.PreferenceManager 
          return (T) DatabaseModule_ProvidePreferenceManagerFactory.providePreferenceManager(singletonCImpl.databaseModule, ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 9: // com.carelink.app.data.repository.AppointmentRepository 
          return (T) RepositoryModule_ProvideAppointmentRepositoryFactory.provideAppointmentRepository(singletonCImpl.repositoryModule, singletonCImpl.provideAppointmentDaoProvider.get(), singletonCImpl.provideAppointmentApiProvider.get());

          case 10: // com.carelink.app.data.local.dao.AppointmentDao 
          return (T) DatabaseModule_ProvideAppointmentDaoFactory.provideAppointmentDao(singletonCImpl.databaseModule, singletonCImpl.provideDatabaseProvider.get());

          case 11: // com.carelink.app.data.remote.api.AppointmentApi 
          return (T) NetworkModule_ProvideAppointmentApiFactory.provideAppointmentApi(singletonCImpl.networkModule, singletonCImpl.provideRetrofitProvider.get());

          case 12: // com.carelink.app.data.repository.CheckinRepository 
          return (T) RepositoryModule_ProvideCheckinRepositoryFactory.provideCheckinRepository(singletonCImpl.repositoryModule, singletonCImpl.provideCheckinDaoProvider.get(), singletonCImpl.provideCheckinTaskDaoProvider.get(), singletonCImpl.provideCheckinApiProvider.get());

          case 13: // com.carelink.app.data.local.dao.CheckinDao 
          return (T) DatabaseModule_ProvideCheckinDaoFactory.provideCheckinDao(singletonCImpl.databaseModule, singletonCImpl.provideDatabaseProvider.get());

          case 14: // com.carelink.app.data.local.dao.CheckinTaskDao 
          return (T) DatabaseModule_ProvideCheckinTaskDaoFactory.provideCheckinTaskDao(singletonCImpl.databaseModule, singletonCImpl.provideDatabaseProvider.get());

          case 15: // com.carelink.app.data.remote.api.CheckinApi 
          return (T) NetworkModule_ProvideCheckinApiFactory.provideCheckinApi(singletonCImpl.networkModule, singletonCImpl.provideRetrofitProvider.get());

          case 16: // com.carelink.app.data.repository.CareNoteRepository 
          return (T) RepositoryModule_ProvideCareNoteRepositoryFactory.provideCareNoteRepository(singletonCImpl.repositoryModule, singletonCImpl.provideCareNoteDaoProvider.get(), singletonCImpl.provideNoteApiProvider.get());

          case 17: // com.carelink.app.data.local.dao.CareNoteDao 
          return (T) DatabaseModule_ProvideCareNoteDaoFactory.provideCareNoteDao(singletonCImpl.databaseModule, singletonCImpl.provideDatabaseProvider.get());

          case 18: // com.carelink.app.data.remote.api.NoteApi 
          return (T) NetworkModule_ProvideNoteApiFactory.provideNoteApi(singletonCImpl.networkModule, singletonCImpl.provideRetrofitProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
