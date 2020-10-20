package com.mayburger.gojekuiclone.di

import android.app.Application
import android.content.Context
import com.mayburger.gojekuiclone.data.AppDataManager
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.data.hawk.AppHawkHelper
import com.mayburger.gojekuiclone.data.hawk.HawkHelper
import com.mayburger.gojekuiclone.util.rx.AppSchedulerProvider
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }



    @Provides
    @Singleton
    internal fun provideHawkHelper(appHawkHelper: AppHawkHelper): HawkHelper = appHawkHelper

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}