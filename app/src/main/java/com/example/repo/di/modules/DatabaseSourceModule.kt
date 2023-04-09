package com.example.repo.di.modules

import android.content.Context
import androidx.room.Room
import com.example.repo.data.local.db.RepoDao
import com.example.repo.data.local.db.RepoDatabase
import com.example.repo.data.local.db.config.DatabaseSourceBuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseSourceBindModule::class])
object DatabaseSourceModule {

    @Singleton
    @Provides
    fun provideRoomDataBase(context: Context): RepoDatabase =
        Room.databaseBuilder(
            context,
            RepoDatabase::class.java,
            DatabaseSourceBuildConfig.DATABASE_NAME
        ).build()

    @Provides
    fun provideDao(repoDatabase: RepoDatabase): RepoDao =
        repoDatabase.repoDao()
}