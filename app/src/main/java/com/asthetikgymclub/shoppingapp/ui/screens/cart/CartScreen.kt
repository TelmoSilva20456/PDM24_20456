package com.asthetikgymclub.shoppingapp.ui.screens.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.asthetikgymclub.shoppingapp.data.model.CartItem
import com.asthetikgymclub.shoppingapp.navigation.Screen
import com.asthetikgymclub.shoppingapp.ui.screens.cart.viewmodel.CartViewModel

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = hiltViewModel()
) {
    val cartState = viewModel.cart.collectAsState()

    cartState.value?.let { result ->
        if (result.isSuccess) {
            val cart = result.getOrNull()
            if (cart != null) {
                Column(modifier = Modifier.padding(16.dp)) {
                    LazyColumn {
                        items(cart.items) { item ->
                            CartItem(item = item)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Subtotal: $${cart.subtotal}")
                    Text(text = "Shipping: $${cart.shipping}")
                    Text(text = "Total: $${cart.total}")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { navController.navigate(Screen.Checkout.route) }) {
                        Text(text = "Checkout")
                    }
                }
            }
        } else {
            Text(text = "Error: ${result.exceptionOrNull()?.message}")
        }
    } ?: CircularProgressIndicator()
}

@Composable
fun CartItem(item: CartItem) {
    Row {
        Text(text = item.product.name)
        Text(text = "x${item.quantity}")
        Text(text = "$${item.product.price * item.quantity}")
    }
}
