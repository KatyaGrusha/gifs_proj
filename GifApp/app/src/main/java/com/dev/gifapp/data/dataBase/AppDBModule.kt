package com.dev.gifapp.data.dataBase

import android.content.Context
import com.dev.gifapp.data.dataBase.dao.GifDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDBModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideGifDao(appDatabase: AppDatabase): GifDao {
        return appDatabase.projectDao()
    }
}