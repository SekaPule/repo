package com.example.repo.data.repository

import com.example.repo.data.DataProvider
import com.example.repo.data.internet.Api
import com.example.repo.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository(
    private val api: Api?,
    private val dataProvider: DataProvider
) {
    fun getNews(): Flow<List<News>> {
        return flow {
            try {
                emit(api!!.getNewsFromServer().news)
            } catch (e: Throwable) {
                emit(dataProvider.getNewsFromAssets())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getFilters(): Flow<String> {
        return flow {
            try {
                emit(api!!.getFiltersFromServer().toString())
            } catch (e: Throwable) {
                emit(dataProvider.getFilterItemsFromAssetsJson())
            }
        }.flowOn(Dispatchers.IO)
    }
}