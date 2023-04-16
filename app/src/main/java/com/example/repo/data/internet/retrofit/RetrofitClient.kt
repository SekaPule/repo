package com.example.repo.data.internet.retrofit

import com.example.data.remote.RepoApiSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://af1218b4-fa91-4006-8050-b3f4c260162d.mock.pstmn.io/"
    val retrofitService: RepoApiSource by lazy { initRetrofit().create(RepoApiSource::class.java) }

    private fun initRetrofit(): Retrofit{
        val retrofitObj: Retrofit
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        retrofitObj = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return retrofitObj
    }
}