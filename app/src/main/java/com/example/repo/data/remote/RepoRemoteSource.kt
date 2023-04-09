package com.example.repo.data.remote

import com.example.repo.domain.model.NewsList
import com.google.gson.JsonObject

interface RepoRemoteSource {

    suspend fun getNewsFromServer(): NewsList

    suspend fun getFiltersFromServer(): JsonObject
}