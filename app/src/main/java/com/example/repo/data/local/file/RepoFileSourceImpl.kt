package com.example.repo.data.local.file

import android.content.Context
import com.example.repo.data.local.file.config.FileSourceBuildConfig.FILTER_ITEM_FILE_NAME
import com.example.repo.data.local.file.config.FileSourceBuildConfig.NEWS_FILE_NAME
import com.example.repo.domain.model.Filter
import com.example.repo.domain.model.FilterList
import com.example.repo.domain.model.News
import com.example.repo.domain.model.NewsList
import com.google.gson.Gson
import java.io.BufferedReader
import javax.inject.Inject

class RepoFileSourceImpl @Inject constructor(private val context: Context) : RepoFileSource {

    override fun getFiltersFromAssets(): List<Filter> {
        val jsonText = context
            .assets
            .open(FILTER_ITEM_FILE_NAME)
            .bufferedReader()
            .use(BufferedReader::readText)

        return Gson().fromJson(jsonText, FilterList::class.java).filters
    }

    override fun getFiltersFromAssetsJson(): String =
        context
            .assets
            .open(FILTER_ITEM_FILE_NAME)
            .bufferedReader()
            .use(BufferedReader::readText)


    override fun getNewsFromAssets(): List<News> {
        val jsonText = context
            .assets
            .open(NEWS_FILE_NAME)
            .bufferedReader()
            .use(BufferedReader::readText)

        return Gson().fromJson(jsonText, NewsList::class.java).news
    }

    companion object {
    }
}