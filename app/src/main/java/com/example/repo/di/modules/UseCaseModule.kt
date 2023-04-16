package com.example.repo.di.modules

import com.example.repo.domain.interactor.GetNewsUseCaseImpl
import com.example.data.repository.RepoRepository
import dagger.Module
import dagger.Provides

@Module(includes = [UseCaseBindingModule::class])
object UseCaseModule {

    @Provides
    fun provideGetNewsUseCase(repository: RepoRepository): GetNewsUseCaseImpl =
        GetNewsUseCaseImpl(repository = repository)
}