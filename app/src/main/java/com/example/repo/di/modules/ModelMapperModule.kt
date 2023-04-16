package com.example.repo.di.modules

import com.example.repo.presentation.base.mapper.FilterViewMapperImpl
import com.example.repo.presentation.base.mapper.NewsViewMapperImpl
import dagger.Module
import dagger.Provides

@Module(includes = [ModelMapperBindModule::class])
object ModelMapperModule {

    @Provides
    fun provideFilterViewMapper(): FilterViewMapperImpl =
        FilterViewMapperImpl()

    @Provides
    fun provideNewsViewMapper(): NewsViewMapperImpl =
        NewsViewMapperImpl()
}