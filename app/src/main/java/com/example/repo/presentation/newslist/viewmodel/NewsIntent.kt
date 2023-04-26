package com.example.repo.presentation.newslist.viewmodel

import com.example.search_feature.presentation.model.NewsView


sealed class NewsIntent {

    data class DetailsNewsIntent(val newsView: NewsView) : NewsIntent()
    object FilterNewsIntent : NewsIntent()
}
