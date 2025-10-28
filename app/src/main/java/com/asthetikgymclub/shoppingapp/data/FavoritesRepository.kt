package com.asthetikgymclub.shoppingapp.data

import com.asthetikgymclub.shoppingapp.data.model.Product
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun getFavorites(): Flow<Result<List<Product>>>
    suspend fun addToFavorites(product: Product): Flow<Result<Unit>>
    suspend fun removeFromFavorites(product: Product): Flow<Result<Unit>>
}
