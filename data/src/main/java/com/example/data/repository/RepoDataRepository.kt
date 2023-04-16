package com.example.data.repository

import com.example.data.local.db.RepoDatabaseSource
import com.example.data.local.file.RepoFileSource
import com.example.data.mapper.FilterMapperImpl
import com.example.data.mapper.NewsMapperImpl
import com.example.data.model.FilterList
import com.example.data.model.News
import com.example.data.remote.RepoRemoteSource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RepoDataRepository @Inject constructor(
    private val repoApiSource: RepoRemoteSource,
    private val repoFileSource: RepoFileSource,
    private val dao: RepoDatabaseSource,
    private val newsMapper: NewsMapperImpl,
    private val filterMapper: FilterMapperImpl
) : RepoRepository {

    override fun getNews(): Flow<List<News>> {
        return flow {
            try {
                emitAll(dao.getAllNews().map { entityList ->
                    entityList.map { entity ->
                        newsMapper.mapFromEntity(type = entity)
                    }
                })
            } catch (e: Throwable) {
                emit(repoFileSource.getNewsFromAssets())
            }

        }.flowOn(Dispatchers.IO)
    }

    override fun getFilters(): Flow<String> {
        return flow {
            try {
                emitAll(dao.getAllFilters().map { entityList ->
                    Gson().toJson(FilterList(
                        filters = entityList.map { entity ->
                            filterMapper.mapFromEntity(type = entity)
                        }
                    ))
                })
            } catch (e: Throwable) {
                emit(repoFileSource.getFiltersFromAssetsJson())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun initDataForCurrentSession() {
        val newsList = try {
            repoApiSource.getNewsFromServer().news
        } catch (e: Throwable) {
            repoFileSource.getNewsFromAssets()
        }
        dao.insertAllNews(
            news = newsList.map { news ->
                newsMapper.mapToEntity(type = news)
            }
        )

        val filterList = try {
            Gson().fromJson<FilterList?>(
                repoApiSource.getFiltersFromServer(),
                object : TypeToken<FilterList>() {}.type
            ).filters
        } catch (e: Throwable) {
            repoFileSource.getFiltersFromAssets()
        }
        dao.insertAllFilters(
            filters = filterList.map { filter ->
                filterMapper.mapToEntity(type = filter)
            }
        )
    }
}