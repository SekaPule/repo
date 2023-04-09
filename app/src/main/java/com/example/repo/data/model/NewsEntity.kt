package com.example.repo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.repo.data.local.db.config.DatabaseSourceBuildConfig.NEWS_TABLE_NAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = NEWS_TABLE_NAME)
data class NewsEntity(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "category")
    val category: List<String>?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "organization")
    val organization: String?,

    @ColumnInfo(name = "date")
    val date: String?,

    @ColumnInfo(name = "location")
    val location: String?,

    @ColumnInfo(name = "phone_numbers")
    val phoneNumbers: List<String>?,

    @ColumnInfo(name = "title_description")
    val titleDescription: String?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "sub_description")
    val subDescription: String?,

    @ColumnInfo(name = "is_checked")
    var isChecked: Boolean = false
)

class ListConverter {

    @TypeConverter
    fun fromStringArrayList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringArrayList(value: String): List<String> {
        return try {
            val type = object : TypeToken<List<String>>() {}.type
            Gson().fromJson(value, type)
        } catch (e: Exception) {
            listOf()
        }
    }
}