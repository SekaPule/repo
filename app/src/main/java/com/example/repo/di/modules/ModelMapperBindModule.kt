package com.example.repo.di.modules

import com.example.repo.presentation.base.mapper.NewsViewMapperImpl
import com.example.search_feature.presentation.mapper.NewsViewMapper
import dagger.Binds
import dagger.Module

@Module
interface ModelMapperBindModule {

    @Binds
    fun bindNewsViewMapper(newsViewMapperImpl: NewsViewMapperImpl): NewsViewMapper

//    @Binds
//    fun bindFilterViewMapper(FilterViewMapperImpl: FilterViewMapperImpl): FilterViewMapperImpl
}