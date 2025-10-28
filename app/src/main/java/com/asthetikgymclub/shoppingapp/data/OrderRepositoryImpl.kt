package com.asthetikgymclub.shoppingapp.data

import com.asthetikgymclub.shoppingapp.data.model.Order
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : OrderRepository {
    override suspend fun placeOrder(order: Order): Flow<Result<Unit>> = flow {
        try {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                val newOrder = order.copy(userId = userId)
                firestore.collection("orders").add(newOrder).await()
                emit(Result.success(Unit))
            } else {
                emit(Result.failure(Exception("User not logged in")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getOrders(): Flow<Result<List<Order>>> = flow {
        try {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                val result = firestore.collection("orders")
                    .whereEqualTo("userId", userId)
                    .get()
                    .await()
                val orders = result.toObjects(Order::class.java)
                emit(Result.success(orders))
            } else {
                emit(Result.failure(Exception("User not logged in")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
