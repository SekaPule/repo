package com.example.repo.di.modules

import com.example.repo.domain.interactor.GetNewsUseCaseImpl
import com.example.search_feature.interactor.GetNewsUseCase
import dagger.Binds
import dagger.Module

@Module
interface UseCaseBindingModule {

    @Binds
    fun bindGetNewsUseCase(getNewsUseCase: GetNewsUseCaseImpl): GetNewsUseCase
}