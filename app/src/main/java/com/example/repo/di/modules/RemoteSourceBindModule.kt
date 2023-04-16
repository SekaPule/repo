package com.example.repo.di.modules

import com.example.data.remote.RepoApiSource
import com.example.data.remote.RepoRemoteSource
import dagger.Binds
import dagger.Module

@Module
interface RemoteSourceBindModule {

    @Binds
    fun bindRemoteSource(repoApiSource: RepoApiSource): RepoRemoteSource
}