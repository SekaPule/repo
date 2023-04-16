package com.example.repo.di.modules

import com.example.repo.presentation.base.mapper.FilterViewMapper
import com.example.repo.presentation.base.mapper.FilterViewMapperImpl
import com.example.repo.presentation.base.mapper.NewsViewMapperImpl
import com.example.search_feature.presentation.mapper.NewsViewMapper
import dagger.Binds
import dagger.Module

@Module
interface ModelViewMapperBindModule {

    @Binds
    fun bindNewsViewMapper(newsViewMapperImpl: NewsViewMapperImpl): NewsViewMapper

    @Binds
    fun bindFilterViewMapper(filterViewMapperImpl: FilterViewMapperImpl): FilterViewMapper
}