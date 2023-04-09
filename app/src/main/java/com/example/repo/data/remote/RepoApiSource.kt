package com.example.repo.data.remote

import com.example.repo.data.remote.config.RemoteSourceBuildConfig.REMOTE_GET_FILTERS_PATH
import com.example.repo.data.remote.config.RemoteSourceBuildConfig.REMOTE_GET_NEWS_PATH
import com.example.repo.domain.model.NewsList
import com.google.gson.JsonObject
import retrofit2.http.GET

interface RepoApiSource : RepoRemoteSource {

    @GET(REMOTE_GET_NEWS_PATH)
    override suspend fun getNewsFromServer(): NewsList

    @GET(REMOTE_GET_FILTERS_PATH)
    override suspend fun getFiltersFromServer(): JsonObject
}