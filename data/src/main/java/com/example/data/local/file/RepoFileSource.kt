package com.example.data.local.file

import com.example.data.model.Filter
import com.example.data.model.News

interface RepoFileSource {

    fun getFiltersFromAssets(): List<Filter>

    fun getFiltersFromAssetsJson(): String

    fun getNewsFromAssets(): List<News>
}