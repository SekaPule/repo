package com.example.repo.presentation.details.vm

import com.example.search_feature.presentation.model.NewsView

sealed class DetailsIntent {

    object CloseDetailsIntent: DetailsIntent()
    data class InitDetailsDataIntent(val newsView: NewsView): DetailsIntent()
}
