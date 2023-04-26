package com.example.auth_feature.presentation.vm

sealed class AuthIntent {

    data class EmailChangedIntent(val email: String) : AuthIntent()
    data class PasswordChangedIntent(val password: String) : AuthIntent()
    object LoginIntent : AuthIntent()
    object CloseIntent : AuthIntent()
}
