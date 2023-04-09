package com.example.repo.di.modules

import com.example.repo.data.local.db.RepoDao
import com.example.repo.data.local.db.RepoDatabaseSource
import dagger.Binds
import dagger.Module

@Module
interface DatabaseSourceBindModule {

    @Binds
    fun bindDataBaseSource(repoDao: RepoDao): RepoDatabaseSource
}
