package com.averito.mimi.di

import com.averito.mimi.core.utils.AppLogger
import com.averito.mimi.data.loggers.DefaultAppLoggerImpl
import com.averito.mimi.di.qualifiers.DefaultAppLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {
    @Provides
    @Singleton
    @DefaultAppLogger
    fun provideDefaultAppLogger(): AppLogger {
        return DefaultAppLoggerImpl()
    }
}