package com.example.repo.di.modules

import com.example.repo.data.local.file.RepoFileSource
import com.example.repo.data.local.file.RepoFileSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface FileSourceBindModule {

    @Binds
    fun bindFileSource(repoFileSourceImpl: RepoFileSourceImpl): RepoFileSource
}