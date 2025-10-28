package com.asthetikgymclub.shoppingapp.ui.screens.orders

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
import com.asthetikgymclub.shoppingapp.data.model.Order
import com.asthetikgymclub.shoppingapp.ui.screens.orders.viewmodel.OrdersViewModel

@Composable
fun OrdersScreen(
    viewModel: OrdersViewModel = hiltViewModel()
) {
    val ordersState = viewModel.orders.collectAsState()

    ordersState.value?.let { result ->
        if (result.isSuccess) {
            val orders = result.getOrNull()
            if (orders != null) {
                LazyColumn {
                    items(orders) { order ->
                        OrderCard(order = order)
                    }
                }
            }
        } else {
            Text(text = "Error: ${result.exceptionOrNull()?.message}")
        }
    } ?: CircularProgressIndicator()
}

@Composable
fun OrderCard(order: Order) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column {
            Text(text = "Order #${order.id}")
            Text(text = "Total: $${order.total}")
            Text(text = "Status: ${order.status}")
        }
    }
}
