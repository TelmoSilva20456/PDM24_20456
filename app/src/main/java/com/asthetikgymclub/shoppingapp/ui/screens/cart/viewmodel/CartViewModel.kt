package com.asthetikgymclub.shoppingapp.ui.screens.cart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asthetikgymclub.shoppingapp.data.CartRepository
import com.asthetikgymclub.shoppingapp.data.model.Cart
import com.asthetikgymclub.shoppingapp.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _cart = MutableStateFlow<Result<Cart>?>(null)
    val cart: StateFlow<Result<Cart>?> = _cart

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

    fun addToCart(product: Product, quantity: Int) {
        viewModelScope.launch {
            cartRepository.addToCart(product, quantity).collect {
                getCart()
            }
        }
    }

    fun removeFromCart(product: Product) {
        viewModelScope.launch {
            cartRepository.removeFromCart(product).collect {
                getCart()
            }
        }
    }

    fun updateCart(product: Product, quantity: Int) {
        viewModelScope.launch {
            cartRepository.updateCart(product, quantity).collect {
                getCart()
            }
        }
    }
}
