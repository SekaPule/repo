package com.example.auth_feature.interactor

import com.example.auth_feature.interactor.model.User
import javax.inject.Inject

class AuthValidateUseCase @Inject constructor() {

    fun execute(user: User): Boolean =
        user.email.length > 5 && user.password.length > 5
}