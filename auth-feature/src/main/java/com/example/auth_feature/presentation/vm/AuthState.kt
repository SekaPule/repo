package com.example.auth_feature.presentation.vm

data class AuthState(
    val email: String = "",
    val password: String = "",
    val isValid: Boolean = false
)