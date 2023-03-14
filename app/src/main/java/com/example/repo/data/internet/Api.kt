package com.example.repo.data.internet

import com.example.repo.model.NewsList
import com.google.gson.JsonObject
import retrofit2.http.GET

interface Api {
    @GET("/news")
    suspend fun getNewsFromServer(): NewsList

    @GET("/categories")
    suspend fun getFiltersFromServer(): JsonObject
}