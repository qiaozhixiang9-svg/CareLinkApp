package com.carelink.app;

import android.app.Application;
import dagger.hilt.android.HiltAndroidApp;

/**
 * Application 类，使用 Hilt 注入
 */
@HiltAndroidApp
public class CareLinkApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
