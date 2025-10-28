package com.asthetikgymclub.shoppingapp.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.asthetikgymclub.shoppingapp.data.model.Product
import com.asthetikgymclub.shoppingapp.navigation.Screen
import com.asthetikgymclub.shoppingapp.ui.screens.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val productsState = viewModel.products.collectAsState()
    val searchQuery = remember { mutableStateOf("") }

    Column {
        TextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            label = { Text("Search") }
        )

        productsState.value?.let { result ->
            if (result.isSuccess) {
                val products = result.getOrNull()
                if (products != null) {
                    val filteredProducts = products.filter {
                        it.name.contains(searchQuery.value, ignoreCase = true)
                    }
                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        items(filteredProducts) { product ->
                            ProductCard(product = product) {
                                navController.navigate(Screen.ProductDetails.createRoute(product.id))
                            }
                        }
                    }
                }
            } else {
                Text(text = "Error: ${result.exceptionOrNull()?.message}")
            }
        } ?: CircularProgressIndicator()
    }
}

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(modifier = Modifier.padding(8.dp), onClick = onClick) {
        Column {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name
            )
            Text(text = product.name)
            Text(text = "$${product.price}")
        }
    }
}
