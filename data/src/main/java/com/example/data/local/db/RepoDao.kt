package com.example.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.FilterEntity
import com.example.data.model.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao : RepoDatabaseSource {

    @Query("SELECT * FROM filters_table")
    override fun getAllFilters(): Flow<List<FilterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertAllFilters(filters: List<FilterEntity>)

    @Query("SELECT * FROM news_table")
    override fun getAllNews(): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertAllNews(news: List<NewsEntity>)
}