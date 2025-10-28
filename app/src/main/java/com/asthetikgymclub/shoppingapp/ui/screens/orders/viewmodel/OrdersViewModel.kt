package com.asthetikgymclub.shoppingapp.ui.screens.orders.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asthetikgymclub.shoppingapp.data.OrderRepository
import com.asthetikgymclub.shoppingapp.data.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _orders = MutableStateFlow<Result<List<Order>>?>(null)
    val orders: StateFlow<Result<List<Order>>?> = _orders

    init {
        getOrders()
    }

    private fun getOrders() {
        viewModelScope.launch {
            orderRepository.getOrders().collect {
                _orders.value = it
            }
        }
    }
}
