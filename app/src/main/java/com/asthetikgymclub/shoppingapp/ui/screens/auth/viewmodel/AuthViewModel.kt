package com.asthetikgymclub.shoppingapp.ui.screens.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asthetikgymclub.shoppingapp.data.AuthRepository
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<Result<AuthResult>?>(null)
    val loginState: StateFlow<Result<AuthResult>?> = _loginState

    private val _signupState = MutableStateFlow<Result<AuthResult>?>(null)
    val signupState: StateFlow<Result<AuthResult>?> = _signupState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password).collect {
                _loginState.value = it
            }
        }
    }

    fun signup(name: String, email: String, password: String) {
        viewModelScope.launch {
            authRepository.signup(name, email, password).collect {
                _signupState.value = it
            }
        }
    }
}
