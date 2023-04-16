package com.example.data.remote

import com.example.data.model.NewsList
import com.google.gson.JsonObject

interface RepoRemoteSource {

    suspend fun getNewsFromServer(): NewsList

    suspend fun getFiltersFromServer(): JsonObject
}