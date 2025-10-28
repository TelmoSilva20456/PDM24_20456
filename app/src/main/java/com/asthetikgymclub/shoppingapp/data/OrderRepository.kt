package com.asthetikgymclub.shoppingapp.data

import com.asthetikgymclub.shoppingapp.data.model.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun placeOrder(order: Order): Flow<Result<Unit>>
    suspend fun getOrders(): Flow<Result<List<Order>>>
}
