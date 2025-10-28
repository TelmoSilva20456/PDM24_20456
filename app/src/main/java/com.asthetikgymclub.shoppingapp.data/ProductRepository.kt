package com.asthetikgymclub.shoppingapp.data

import com.asthetikgymclub.shoppingapp.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): Flow<Result<List<Product>>>
    suspend fun getProductById(productId: String): Flow<Result<Product>>
}
