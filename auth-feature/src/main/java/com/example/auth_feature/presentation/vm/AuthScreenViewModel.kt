package com.example.auth_feature.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.auth_feature.interactor.AuthValidateUseCase
import com.example.auth_feature.interactor.model.User
import javax.inject.Inject

class AuthScreenViewModel @Inject constructor(private val authValidateUseCase: AuthValidateUseCase) :
    ViewModel() {

    private val _state = MutableLiveData(AuthState())
    val stateObservable: LiveData<AuthState> = _state

    private val _isAuthorized = MutableLiveData(false)
    val isAuthorized: LiveData<Boolean> = _isAuthorized

    private val _close = MutableLiveData(false)
    val close: LiveData<Boolean> = _close

    fun obtainIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.EmailChangedIntent -> _state.also {
                it.postValue(
                    it.value!!.copy(
                        email = intent.email,
                        isValid = validate(it.value!!.email , it.value!!.password)
                    )
                )
            }
            is AuthIntent.PasswordChangedIntent -> _state.also {
                it.postValue(
                    it.value!!.copy(
                        password = intent.password,
                        isValid = validate(it.value!!.email , it.value!!.password)
                    )
                )
            }
            AuthIntent.CloseIntent -> _close.postValue(true)
            AuthIntent.LoginIntent -> _isAuthorized.postValue(true)
        }
    }

    private fun validate(email: String, password: String): Boolean =
        authValidateUseCase.execute(user = User(email = email, password = password))
}