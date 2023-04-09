package com.example.repo.presentation.auth.vm

sealed class AuthState {

    object ValidationSuccess : AuthState()
    object ValidationError : AuthState()
}
