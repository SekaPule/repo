package com.example.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.model.FilterEntity
import com.example.data.model.ListConverter
import com.example.data.model.NewsEntity

@Database(entities = [NewsEntity::class, FilterEntity::class], version = 2)
@TypeConverters(ListConverter::class)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDao
}