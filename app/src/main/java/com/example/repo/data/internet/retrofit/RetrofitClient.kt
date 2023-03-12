package com.example.repo.data.internet.retrofit

import com.example.repo.data.internet.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private const val BASE_URL = "https://af1218b4-fa91-4006-8050-b3f4c260162d.mock.pstmn.io/"

        fun configureRetrofit(): Api? {
            var retrofit: Retrofit? = null

            if (retrofit == null) {
                synchronized(RetrofitClient::class.java)
                {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.level = HttpLoggingInterceptor.Level.BODY

                    val client = OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build()

                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                        .build()
                }
            }
            
            return retrofit?.create(Api::class.java)
        }

    }
}