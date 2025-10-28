package com.asthetikgymclub.shoppingapp.ui.screens.productdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asthetikgymclub.shoppingapp.data.ProductRepository
import com.asthetikgymclub.shoppingapp.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _product = MutableStateFlow<Result<Product>?>(null)
    val product: StateFlow<Result<Product>?> = _product

    fun getProductById(productId: String) {
        viewModelScope.launch {
            productRepository.getProductById(productId).collect {
                _product.value = it
            }
        }
    }
}
