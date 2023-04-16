package com.example.repo.di.modules

import com.example.data.mapper.FilterMapperImpl
import com.example.data.mapper.NewsMapperImpl
import dagger.Binds
import dagger.Module

@Module
interface ModelMapperBindsModule {

    @Binds
    fun bindNewsMapper(newsMapperImpl: NewsMapperImpl): NewsMapperImpl

    @Binds
    fun bindFilterMapper(filterMapperImpl: FilterMapperImpl): FilterMapperImpl
}