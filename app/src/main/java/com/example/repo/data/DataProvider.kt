package com.example.repo.data

import android.content.Context
import com.example.repo.model.FilterItem
import com.example.repo.model.FilterList
import com.example.repo.model.News
import com.example.repo.model.NewsList
import com.google.gson.Gson
import java.io.BufferedReader

class DataProvider(private val context: Context) {

    fun getFilterItemsFromAssets(): List<FilterItem> {
        val jsonText = context
            .assets
            .open(FILTER_ITEM_FILE_NAME)
            .bufferedReader()
            .use(BufferedReader::readText)

        return Gson().fromJson(jsonText, FilterList::class.java).filters
    }

    fun getNewsFromAssets(): List<News> {
        val jsonText = context
            .assets
            .open(NEWS_FILE_NAME)
            .bufferedReader()
            .use(BufferedReader::readText)

        return Gson().fromJson(jsonText, NewsList::class.java).news
    }

    companion object {
        private const val FILTER_ITEM_FILE_NAME = "filters.json"
        private const val NEWS_FILE_NAME = "news.json"
    }
}