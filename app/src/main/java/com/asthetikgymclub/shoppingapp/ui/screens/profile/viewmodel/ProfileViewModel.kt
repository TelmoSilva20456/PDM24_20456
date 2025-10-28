package com.asthetikgymclub.shoppingapp.ui.screens.profile.viewmodel

import androidx to.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asthetikgymclub.shoppingapp.data.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _user = MutableStateFlow<FirebaseUser?>(null)
    val user: StateFlow<FirebaseUser?> = _user

    private val _updateProfileState = MutableStateFlow<Result<Unit>?>(null)
    val updateProfileState: StateFlow<Result<Unit>?> = _updateProfileState

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            _user.value = authRepository.getCurrentUser()
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}
