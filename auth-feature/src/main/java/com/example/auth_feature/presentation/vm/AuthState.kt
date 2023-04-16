package com.example.auth_feature.presentation.vm

sealed class AuthState {

    object ValidationSuccess : AuthState()
    object ValidationError : AuthState()
}
