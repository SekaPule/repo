package com.example.repo.di.modules

import com.example.repo.presentation.base.mapper.FilterViewMapperImpl
import dagger.Module
import dagger.Provides

@Module(includes = [ModelViewMapperBindModule::class])
object ModelViewMapperModule {

    @Provides
    fun provideFilterViewMapper(): FilterViewMapperImpl =
        FilterViewMapperImpl()
}