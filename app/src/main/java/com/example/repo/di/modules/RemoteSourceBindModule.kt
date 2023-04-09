package com.example.repo.di.modules

import com.example.repo.data.remote.RepoApiSource
import com.example.repo.data.remote.RepoRemoteSource
import dagger.Binds
import dagger.Module

@Module
interface RemoteSourceBindModule {

    @Binds
    fun bindRemoteSource(repoApiSource: RepoApiSource): RepoRemoteSource
}