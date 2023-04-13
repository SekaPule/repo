package com.example.auth_feature.presentation.vm

import com.example.auth_feature.interactor.model.User

sealed class AuthIntent {

    data class AuthValidateIntent(val user: User) : AuthIntent()
}
