package com.example.repo.data.repository

import com.example.repo.data.DataProvider
import com.example.repo.data.db.RepoDao
import com.example.repo.data.db.entities.toFilterItemModel
import com.example.repo.data.db.entities.toNewsModel
import com.example.repo.data.internet.Api
import com.example.repo.model.FilterList
import com.example.repo.model.News
import com.example.repo.model.toFilterItemEntity
import com.example.repo.model.toNewsEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository(
    private val api: Api,
    private val dataProvider: DataProvider,
    private val dao: RepoDao
) {
    fun getNews(): Flow<List<News>> {
        return flow {
            try {
                dao.getAllNews().collect { entityList ->
                    emit(
                        entityList.map { newsEntity ->
                            newsEntity.toNewsModel()
                        }
                    )
                }
            } catch (e: Throwable) {
                emit(dataProvider.getNewsFromAssets())
            }

        }.flowOn(Dispatchers.IO)
    }

    fun getFilters(): Flow<String> {
        return flow {
            try {
                dao.getAllFilters().collect { entityList ->
                    emit(
                        Gson().toJson(
                            FilterList(
                                filters = entityList.map {
                                    it.toFilterItemModel()
                                }
                            )
                        )
                    )
                }
            } catch (e: Throwable) {
                emit(dataProvider.getFilterItemsFromAssetsJson())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun initDataForCurrentSession() {
        dao.insertAllNews(
            api.getNewsFromServer().news.map {
                it.toNewsEntity()
            }
        )
        val filterList: FilterList = Gson().fromJson(
            api.getFiltersFromServer(),
            object : TypeToken<FilterList>() {}.type
        )
        dao.insertAllFilters(
            filterList.filters.map {
                it.toFilterItemEntity()
            }
        )
    }
}