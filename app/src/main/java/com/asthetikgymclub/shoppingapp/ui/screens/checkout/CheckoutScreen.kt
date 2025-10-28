package com.asthetikgymclub.shoppingapp.ui.screens.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import com.asthetikgymclub.shoppingapp.ui.screens.checkout.viewmodel.CheckoutViewModel

@Composable
fun CheckoutScreen(
    viewModel: CheckoutViewModel = hiltViewModel()
) {
    val cartState = viewModel.cart.collectAsState()
    val orderState = viewModel.orderState.collectAsState()
    val shippingAddress = remember { mutableStateOf("") }
    val paymentMethod = remember { mutableStateOf("") }

    cartState.value?.let { result ->
        if (result.isSuccess) {
            val cart = result.getOrNull()
            if (cart != null) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Order Summary")
                    Text(text = "Subtotal: $${cart.subtotal}")
                    Text(text = "Shipping: $${cart.shipping}")
                    Text(text = "Total: $${cart.total}")
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = shippingAddress.value,
                        onValueChange = { shippingAddress.value = it },
                        label = { Text("Shipping Address") }
                    )
                    TextField(
                        value = paymentMethod.value,
                        onValueChange = { paymentMethod.value = it },
                        label = { Text("Payment Method") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        viewModel.placeOrder(shippingAddress.value, paymentMethod.value)
                    }) {
                        Text(text = "Place Order")
                    }
                    orderState.value?.let { orderResult ->
                        if (orderResult.isSuccess) {
                            Text(text = "Order placed successfully")
                        } else {
                            Text(text = "Error: ${orderResult.exceptionOrNull()?.message}")
                        }
                    }
                }
            }
        } else {
            Text(text = "Error: ${result.exceptionOrNull()?.message}")
        }
    } ?: CircularProgressIndicator()
}
