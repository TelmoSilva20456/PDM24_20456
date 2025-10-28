package com.asthetikgymclub.shoppingapp.ui.screens.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asthetikgymclub.shoppingapp.data.model.Product
import com.asthetikgymclub.shoppingapp.ui.screens.favorites.viewmodel.FavoritesViewModel

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val favoritesState = viewModel.favorites.collectAsState()

    favoritesState.value?.let { result ->
        if (result.isSuccess) {
            val favorites = result.getOrNull()
            if (favorites != null) {
                LazyColumn {
                    items(favorites) { product ->
                        FavoriteProductCard(product = product)
                    }
                }
            }
        } else {
            Text(text = "Error: ${result.exceptionOrNull()?.message}")
        }
    } ?: CircularProgressIndicator()
}

@Composable
fun FavoriteProductCard(product: Product) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column {
            Text(text = product.name)
            Text(text = "$${product.price}")
        }
    }
}
