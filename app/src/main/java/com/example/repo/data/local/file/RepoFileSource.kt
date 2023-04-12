package com.example.repo.data.local.file

import com.example.repo.domain.model.Filter
import com.example.repo.domain.model.News

interface RepoFileSource {

    fun getFiltersFromAssets(): List<Filter>

    fun getFiltersFromAssetsJson(): String

    fun getNewsFromAssets(): List<News>
}