package com.example.repo.presentation.auth.vm

import com.example.repo.domain.model.User

sealed class AuthIntent {

    data class AuthValidateIntent(val user: User) : AuthIntent()
}
