package com.example.repo.domain.interactor

import com.example.repo.domain.model.News
import com.example.repo.domain.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository: RepoRepository) {

    fun execute(): Flow<List<News>> = repository.getNews()
}