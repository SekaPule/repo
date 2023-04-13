package com.example.repo.domain.interactor

import com.example.data.model.News
import com.example.data.repository.RepoRepository
import com.example.search_feature.interactor.GetNewsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCaseImpl @Inject constructor(
    private val repository: RepoRepository
) : GetNewsUseCase {

    override fun execute(): Flow<List<News>> = repository.getNews()
}