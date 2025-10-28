package com.asthetikgymclub.shoppingapp.ui.screens.productdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.asthetikgymclub.shoppingapp.navigation.Screen
import com.asthetikgymclub.shoppingapp.ui.screens.cart.viewmodel.CartViewModel
import com.asthetikgymclub.shoppingapp.ui.screens.favorites.viewmodel.FavoritesViewModel
import com.asthetikgymclub.shoppingapp.ui.screens.productdetails.viewmodel.ProductDetailsViewModel

@Composable
fun ProductDetailsScreen(
    productId: String,
    navController: NavController,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    LaunchedEffect(productId) {
        viewModel.getProductById(productId)
    }

    val productState = viewModel.product.collectAsState()

    productState.value?.let { result ->
        if (result.isSuccess) {
            val product = result.getOrNull()
            if (product != null) {
                Column(modifier = Modifier.padding(16.dp)) {
                    AsyncImage(
                        model = product.imageUrl,
                        contentDescription = product.name
                    )
                    Text(text = product.name)
                    Text(text = "$${product.price}")
                    Text(text = product.description)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        cartViewModel.addToCart(product, 1)
                        navController.navigate(Screen.Cart.route)
                    }) {
                        Text(text = "Add to Cart")
                    }
                    Button(onClick = { favoritesViewModel.addToFavorites(product) }) {
                        Text(text = "Add to Favorites")
                    }
                }
            }
        } else {
            Text(text = "Error: ${result.exceptionOrNull()?.message}")
        }
    } ?: CircularProgressIndicator()
}
