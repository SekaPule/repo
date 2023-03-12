package com.example.repo.data.internet

import com.example.repo.model.NewsList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface Api {
    @GET("/news")
    fun getNewsFromServer(): Single<NewsList>

    @GET("/categories")
    fun getFiltersFromServer(): Single<String>
}