package com.example.repo.di.modules

import com.example.data.repository.RepoDataRepository
import com.example.data.repository.RepoRepository
import dagger.Binds
import dagger.Module

@Module
interface RepoRepositoryBindModule {

    @Binds
    fun bindRepoRepository(repoDataRepository: RepoDataRepository): RepoRepository
}