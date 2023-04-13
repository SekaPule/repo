package com.example.data.repository

import com.example.data.model.News
import kotlinx.coroutines.flow.Flow

interface RepoRepository {

    fun getNews(): Flow<List<News>>

    fun getFilters(): Flow<String>

    suspend fun initDataForCurrentSession()
}