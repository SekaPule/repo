package com.example.repo.di.modules

import com.example.repo.data.repository.RepoDataRepository
import com.example.repo.domain.repositories.RepoRepository
import dagger.Binds
import dagger.Module

@Module
interface RepoRepositoryBindModule {

    @Binds
    fun bindRepoRepository(repoDataRepository: RepoDataRepository): RepoRepository
}