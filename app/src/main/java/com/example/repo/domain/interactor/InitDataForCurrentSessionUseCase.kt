package com.example.repo.domain.interactor

import com.example.repo.domain.repositories.RepoRepository
import javax.inject.Inject

class InitDataForCurrentSessionUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) {

    suspend fun execute() {
        repoRepository.initDataForCurrentSession()
    }
}