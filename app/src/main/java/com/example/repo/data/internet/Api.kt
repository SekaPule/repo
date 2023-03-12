package com.example.repo.data.internet

import com.example.repo.model.NewsList
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface Api {
    @GET("/news")
    fun getNewsFromServer(): Flow<NewsList>

    @GET("/categories")
    fun getFiltersFromServer(): Flow<String>
}