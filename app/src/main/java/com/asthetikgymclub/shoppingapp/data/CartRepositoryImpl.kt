package com.asthetikgymclub.shoppingapp.data

import com.asthetikgymclub.shoppingapp.data.model.Cart
import com.asthetikgymclub.shoppingapp.data.model.CartItem
import com.asthetikgymclub.shoppingapp.data.model.Product
import kotlinx.coroutines.flow.Flow
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : CartRepository {

    override suspend fun getCart(): Flow<Result<Cart>> = flow {
        try {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                val result = firestore.collection("users").document(userId)
                    .collection("cart").get().await()
                val items = result.toObjects(CartItem::class.java)
                val subtotal = items.sumOf { it.product.price * it.quantity }
                val shipping = if (subtotal > 0) 10.0 else 0.0
                val total = subtotal + shipping
                emit(Result.success(Cart(items, subtotal, shipping, total)))
            } else {
                emit(Result.failure(Exception("User not logged in")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun addToCart(product: Product, quantity: Int): Flow<Result<Unit>> = flow {
        try {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                val cartItem = CartItem(product, quantity)
                firestore.collection("users").document(userId)
                    .collection("cart").document(product.id).set(cartItem).await()
                emit(Result.success(Unit))
            } else {
                emit(Result.failure(Exception("User not logged in")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun removeFromCart(product: Product): Flow<Result<Unit>> = flow {
        try {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                firestore.collection("users").document(userId)
                    .collection("cart").document(product.id).delete().await()
                emit(Result.success(Unit))
            } else {
                emit(Result.failure(Exception("User not logged in")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun updateCart(product: Product, quantity: Int): Flow<Result<Unit>> = flow {
        try {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                firestore.collection("users").document(userId)
                    .collection("cart").document(product.id).update("quantity", quantity).await()
                emit(Result.success(Unit))
            } else {
                emit(Result.failure(Exception("User not logged in")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
