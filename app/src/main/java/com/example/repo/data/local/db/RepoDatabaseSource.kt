package com.example.repo.data.local.db

import com.example.repo.data.model.FilterEntity
import com.example.repo.data.model.NewsEntity
import kotlinx.coroutines.flow.Flow

interface RepoDatabaseSource {

    fun getAllFilters(): Flow<List<FilterEntity>>

    fun insertAllFilters(filters: List<FilterEntity>)

    fun getAllNews(): Flow<List<NewsEntity>>

    fun insertAllNews(news: List<NewsEntity>)
}