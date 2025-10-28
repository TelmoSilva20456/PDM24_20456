package com.asthetikgymclub.shoppingapp.data.model

data class Cart(
    val items: List<CartItem> = emptyList(),
    val subtotal: Double = 0.0,
    val shipping: Double = 0.0,
    val total: Double = 0.0
)
