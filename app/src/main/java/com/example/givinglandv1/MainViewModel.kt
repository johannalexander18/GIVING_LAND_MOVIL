package com.example.givinglandv1

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    init {
        loadItems()
    }

    private fun loadItems() {
        _items.value = listOf(
            Item(
                images = listOf(
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1")
                ),
                title = "Celular",
                description = "Se intercambia iphone por   dmfjsnfdsnmfdsf sdnfdsfnsdfdfns d sfnsdnfdsnfn c ...",
                additionalInfo = "Intercambio",
                location = "Popayán",
                isFavorite = false
            ),
            Item(
                images = listOf(
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1")
                ),
                title = "auto",
                description = "Se intercambia iphone por ...",
                additionalInfo = "Donacion",
                location = "Popayán",
                isFavorite = false
            ),
            // Agrega más elementos aquí...
        )
    }

    fun toggleFavorite(item: Item) {
        _items.value = _items.value?.map {
            if (it == item) it.copy(isFavorite = !it.isFavorite) else it
        }
    }
}
