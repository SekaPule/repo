package com.example.repo.domain.interactor

import com.example.data.repository.RepoRepository
import javax.inject.Inject

class InitDataForCurrentSessionUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) {

    suspend fun execute() {
        repoRepository.initDataForCurrentSession()
    }
}