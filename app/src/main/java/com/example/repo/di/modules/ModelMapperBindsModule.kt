package com.example.repo.di.modules

import com.example.repo.data.mapper.FilterMapper
import com.example.repo.data.mapper.FilterMapperImpl
import com.example.repo.data.mapper.NewsMapper
import com.example.repo.data.mapper.NewsMapperImpl
import dagger.Binds
import dagger.Module

@Module
interface ModelMapperBindsModule {

    @Binds
    fun bindNewsMapper(newsMapperImpl: NewsMapperImpl): NewsMapper

    @Binds
    fun bindFilterMapper(filterMapperImpl: FilterMapperImpl): FilterMapper
}