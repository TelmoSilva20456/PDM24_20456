package com.asthetikgymclub.shoppingapp.data

import com.asthetikgymclub.shoppingapp.data.model.Cart
import com.asthetikgymclub.shoppingapp.data.model.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getCart(): Flow<Result<Cart>>
    suspend fun addToCart(product: Product, quantity: Int): Flow<Result<Unit>>
    suspend fun removeFromCart(product: Product): Flow<Result<Unit>>
    suspend fun updateCart(product: Product, quantity: Int): Flow<Result<Unit>>
}
