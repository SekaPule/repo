package com.example.repo.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.repo.data.db.entities.FilterItemEntity
import com.example.repo.data.db.entities.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Query("SELECT * FROM news_table")
    fun getAllNews(): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNews(news: List<NewsEntity>)

    @Query("SELECT * FROM filters_table")
    fun getAllFilters(): Flow<List<FilterItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFilters(filters: List<FilterItemEntity>)

    @Query("DELETE FROM news_table")
    fun deleteAllNews()
}