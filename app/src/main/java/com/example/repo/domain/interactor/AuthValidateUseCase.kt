package com.example.repo.domain.interactor

import com.example.repo.domain.model.User
import javax.inject.Inject

class AuthValidateUseCase @Inject constructor() {

    fun execute(user: User): Boolean =
        user.email.length > 5 && user.password.length > 5
}