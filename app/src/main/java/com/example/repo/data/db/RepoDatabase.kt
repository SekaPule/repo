package com.example.repo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.repo.data.db.entities.FilterItemEntity
import com.example.repo.data.db.entities.ListConverter
import com.example.repo.data.db.entities.NewsEntity

@Database(entities = [NewsEntity::class, FilterItemEntity::class], version = 2)
@TypeConverters(ListConverter::class)
abstract class RepoDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao

    companion object {
        private const val DATABASE_NAME = "repo_database"
        const val NEWS_TABLE_NAME = "news_table"
        const val FILTERS_TABLE_NAME = "filters_table"
        private var roomClient: RepoDatabase? = null

        fun configureRoomClient(context: Context): RepoDatabase {
            if (roomClient == null) {
                roomClient = Room.databaseBuilder(
                    context,
                    RepoDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }

            return roomClient!!
        }
    }
}