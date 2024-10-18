package com.samuelokello.roomdemo

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {


    @Provides()
    @Singleton
    fun createDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database1"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(appDb: AppDatabase): UserDao{
        return appDb.userDao()
    }
}