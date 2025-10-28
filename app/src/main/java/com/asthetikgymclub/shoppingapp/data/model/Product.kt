package com.asthetikgymclub.shoppingapp.data.model

data class Product(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
    val category: String = "",
    val description: String = "",
    val sizes: List<String> = emptyList(),
    val colors: List<String> = emptyList()
)
