package com.asthetikgymclub.shoppingapp.data

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<Result<AuthResult>>
    suspend fun signup(name: String, email: String, password: String): Flow<Result<AuthResult>>
    suspend fun logout()
    suspend fun getCurrentUser(): FirebaseUser?
}
