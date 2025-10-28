package com.asthetikgymclub.shoppingapp.ui.screens.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asthetikgymclub.shoppingapp.data.FavoritesRepository
import com.asthetikgymclub.shoppingapp.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow<Result<List<Product>>?>(null)
    val favorites: StateFlow<Result<List<Product>>?> = _favorites

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            favoritesRepository.getFavorites().collect {
                _favorites.value = it
            }
        }
    }

    fun addToFavorites(product: Product) {
        viewModelScope.launch {
            favoritesRepository.addToFavorites(product).collect {
                getFavorites()
            }
        }
    }

    fun removeFromFavorites(product: Product) {
        viewModelScope.launch {
            favoritesRepository.removeFromFavorites(product).collect {
                getFavorites()
            }
        }
    }
}
