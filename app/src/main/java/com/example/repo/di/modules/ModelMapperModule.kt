package com.example.repo.di.modules

import com.example.repo.presentation.base.mapper.FilterViewMapper
import dagger.Module
import dagger.Provides

@Module
object ModelMapperModule {

    @Provides
    fun provideFilterViewMapper(): FilterViewMapper =
        FilterViewMapper()
}