package com.example.repo.presentation.auth.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repo.domain.interactor.AuthValidateUseCase
import com.example.repo.domain.model.User
import javax.inject.Inject

class AuthScreenViewModel @Inject constructor(private val authValidateUseCase: AuthValidateUseCase) : ViewModel() {

    private var state: AuthState = AuthState.ValidationError
        set(value) {
            field = value
            stateObservable.postValue(value)
        }

    val stateObservable: MutableLiveData<AuthState> by lazy {
        MutableLiveData<AuthState>()
    }

    fun obtainIntent(intent: AuthIntent) {
        when(intent) {
            is AuthIntent.AuthValidateIntent -> validate(intent.user)
        }
    }

    private fun validate(user: User) {
        state = if (authValidateUseCase.execute(user = user)) {
            AuthState.ValidationSuccess
        } else {
            AuthState.ValidationError
        }
    }
}