package com.example.givinglandv1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.util.copy

class MainViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    init {
        loadItems()
    }

    private fun loadItems() {
        _items.value = listOf(
            Item(R.drawable.producto1, "Celular", "Se intercambia iphone por ...", "Intercambio", "Popayán", false),
            Item(R.drawable.producto1, "Celular2", "Se intercambia iphone por ...", "Intercambio", "Popayán", false),
            Item(R.drawable.producto1, "Celular2", "Se intercambia iphone por ...", "Intercambio", "Popayán", false),
            Item(R.drawable.producto1, "Celular2", "Se intercambia iphone por ...", "Intercambio", "Popayán", false),
            Item(R.drawable.producto1, "Celular2", "Se intercambia iphone por ...", "Intercambio", "Popayán", false),
            Item(R.drawable.producto1, "Celular2", "Se intercambia iphone por ...", "Intercambio", "Popayán", false),
            Item(R.drawable.producto1, "Celular2", "Se intercambia iphone por ...", "Intercambio", "Popayán", false),
            Item(R.drawable.producto1, "Celular2", "Se intercambia iphone por ...", "Intercambio", "Popayán", false),
        // Agrega más elementos aquí...
        )
    }

    fun toggleFavorite(item: Item) {
        _items.value = _items.value?.map {
            if (it == item) it.copy(isFavorite = !it.isFavorite) else it
        }
    }
}
