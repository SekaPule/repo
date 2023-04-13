package com.example.repo.di.modules

import com.example.data.local.file.RepoFileSource
import com.example.data.local.file.RepoFileSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface FileSourceBindModule {

    @Binds
    fun bindFileSource(repoFileSourceImpl: RepoFileSourceImpl): RepoFileSource
}