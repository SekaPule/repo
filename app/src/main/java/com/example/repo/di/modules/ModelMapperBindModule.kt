package com.example.repo.di.modules

import com.example.data.mapper.FilterMapper
import com.example.data.mapper.FilterMapperImpl
import com.example.data.mapper.NewsMapper
import com.example.data.mapper.NewsMapperImpl
import com.example.repo.presentation.base.mapper.NewsViewMapperImpl
import com.example.search_feature.presentation.mapper.NewsViewMapper
import dagger.Binds
import dagger.Module

@Module
interface ModelMapperBindModule {

    @Binds
    fun bindNewsMapper(newsMapperImpl: NewsMapperImpl): NewsMapper

    @Binds
    fun bindFilterMapper(filterMapperImpl: FilterMapperImpl): FilterMapper
}