package com.example.eko.di

import android.content.Context
import com.example.eko.data.api.ApiHelper
import com.example.eko.data.local.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Moha on 2022-06-08.
 */

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDataBase =
        AppDataBase.getInstance(appContext)

    @Singleton
    @Provides
    fun provideMapLocationDaoDao(database: AppDataBase) = database.mapLocationDao()

}