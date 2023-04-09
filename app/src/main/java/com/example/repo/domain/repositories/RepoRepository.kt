package com.example.repo.domain.repositories

import com.example.repo.domain.model.News
import kotlinx.coroutines.flow.Flow

interface RepoRepository {

    fun getNews(): Flow<List<News>>

    fun getFilters(): Flow<String>

    suspend fun initDataForCurrentSession()
}