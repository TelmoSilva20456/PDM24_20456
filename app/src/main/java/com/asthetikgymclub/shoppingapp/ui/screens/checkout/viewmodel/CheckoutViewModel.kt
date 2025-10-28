package com.asthetikgymclub.shoppingapp.ui.screens.checkout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.asthetikgymclub.shoppingapp.data.CartRepository
import com.asthetikgymclub.shoppingapp.data.OrderRepository
import com.asthetikgymclub.shoppingapp.data.model.Cart
import com.asthetikgymclub.shoppingapp.data.model.Order
import com.asthetikgymclub.shoppingapp.worker.NotificationWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val orderRepository: OrderRepository,
    private val workManager: WorkManager
) : ViewModel() {

    private val _cart = MutableStateFlow<Result<Cart>?>(null)
    val cart: StateFlow<Result<Cart>?> = _cart

    private val _orderState = MutableStateFlow<Result<Unit>?>(null)
    val orderState: StateFlow<Result<Unit>?> = _orderState

    init {
        getCart()
    }

    private fun getCart() {
        viewModelScope.launch {
            cartRepository.getCart().collect {
                _cart.value = it
            }
        }
    }

    fun placeOrder(shippingAddress: String, paymentMethod: String) {
        viewModelScope.launch {
            val cart = _cart.value?.getOrNull()
            if (cart != null) {
                val order = Order(
                    items = cart.items,
                    subtotal = cart.subtotal,
                    shipping = cart.shipping,
                    total = cart.total,
                    shippingAddress = shippingAddress,
                    paymentMethod = paymentMethod
                )
                orderRepository.placeOrder(order).collect {
                    _orderState.value = it
                    if (it.isSuccess) {
                        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>().build()
                        workManager.enqueue(workRequest)
                    }
                }
            }
        }
    }
}
