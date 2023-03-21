package com.example.repo.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.repo.data.db.RepoDatabase.Companion.FILTERS_TABLE_NAME
import com.example.repo.model.FilterItem

@Entity(tableName = FILTERS_TABLE_NAME)
data class FilterItemEntity(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String?,
)

fun FilterItemEntity.toFilterItemModel(): FilterItem = FilterItem(
    id = id,
    name = name
)