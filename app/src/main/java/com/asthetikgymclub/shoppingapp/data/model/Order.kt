package com.asthetikgymclub.shoppingapp.data.model

import java.util.Date

data class Order(
    val id: String = "",
    val userId: String = "",
    val items: List<CartItem> = emptyList(),
    val subtotal: Double = 0.0,
    val shipping: Double = 0.0,
    val total: Double = 0.0,
    val shippingAddress: String = "",
    val paymentMethod: String = "",
    val status: String = "Pending",
    val createdAt: Date = Date()
)
