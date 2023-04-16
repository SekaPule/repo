package com.example.search_feature.interactor

import com.example.data.model.News
import kotlinx.coroutines.flow.Flow

interface GetNewsUseCase {

    fun execute(): Flow<List<News>>
}
