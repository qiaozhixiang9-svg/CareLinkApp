package com.carelink.app.di;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

/**
 * 本地调试版占位模块。
 * Firebase 已移除，保留空模块避免旧目录结构造成混淆。
 */
@Module
@InstallIn(SingletonComponent.class)
public class FirebaseModule {
}
