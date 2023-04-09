package com.example.repo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.repo.data.local.db.config.DatabaseSourceBuildConfig.FILTERS_TABLE_NAME

@Entity(tableName = FILTERS_TABLE_NAME)
data class FilterEntity(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String?,
)