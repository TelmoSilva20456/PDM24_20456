package com.asthetikgymclub.shoppingapp.data

import com.asthetikgymclub.shoppingapp.data.model.Product
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : FavoritesRepository {
    override suspend fun getFavorites(): Flow<Result<List<Product>>> = flow {
        try {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                val result = firestore.collection("users").document(userId)
                    .collection("favorites").get().await()
                val favorites = result.toObjects(Product::class.java)
                emit(Result.success(favorites))
            } else {
                emit(Result.failure(Exception("User not logged in")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun addToFavorites(product: Product): Flow<Result<Unit>> = flow {
        try {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                firestore.collection("users").document(userId)
                    .collection("favorites").document(product.id).set(product).await()
                emit(Result.success(Unit))
            } else {
                emit(Result.failure(Exception("User not logged in")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun removeFromFavorites(product: Product): Flow<Result<Unit>> = flow {
        try {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                firestore.collection("users").document(userId)
                    .collection("favorites").document(product.id).delete().await()
                emit(Result.success(Unit))
            } else {
                emit(Result.failure(Exception("User not logged in")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
