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
                title = "Camiseta",
                description = "Esta en excelente estado,talla s",
                additionalInfo = "Intercambio",
                location = "Cali",
                isFavorite = false
            ),

            Item(
                images = listOf(
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1")
                ),
                title = "Zapatos para hombre",
                description = "Talla 38 estan en excelente estado,poco uso",
                additionalInfo = "Intercambio",
                location = "Manizales",
                isFavorite = false
            ),

            Item(
                images = listOf(
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1")
                ),
                title = "Portatil",
                description = "Esta funcionando perfectamente,poco uso",
                additionalInfo = "Intercambio",
                location = "Bogota",
                isFavorite = false
            ),

            Item(
                images = listOf(
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1")
                ),
                title = "Pantalon para dama",
                description = "Esta nuevo,lo pedi pero me quedo muy exacto",
                additionalInfo = "Intercambio",
                location = "Buga",
                isFavorite = false
            ),

            Item(
                images = listOf(
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1")
                ),
                title = "Huawei P30",
                description = "Lo entrego con sus accesorios,esta bueno",
                additionalInfo = "Intercambio",
                location = "Armenia",
                isFavorite = false
            ),

            Item(
                images = listOf(
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1")
                ),
                title = "Camiseta Hombre",
                description = "Marca polo,solo tiene 2 puestas",
                additionalInfo = "Intercambio",
                location = "Pasto",
                isFavorite = false
            ),

            Item(
                images = listOf(
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1")
                ),
                title = "Bolso",
                description = "Bolso grande para viaje marca TOTTO",
                additionalInfo = "Intercambio",
                location = "Antioquia",
                isFavorite = false
            ),

            Item(
                images = listOf(
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1")
                ),
                title = "Cable carga",
                description = "Se intercambia iphone por   dmfjsnfdsnmfdsf sdnfdsfnsdfdfns d sfnsdnfdsnfn c ...",
                additionalInfo = "Intercambio",
                location = "Santander de quilichao",
                isFavorite = false
            ),

            Item(
                images = listOf(
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1")
                ),
                title = "Bolso para dama",
                description = "Se intercambia iphone por   dmfjsnfdsnmfdsf sdnfdsfnsdfdfns d sfnsdnfdsnfn c ...",
                additionalInfo = "Intercambio",
                location = "Jamundi",
                isFavorite = false
            ),

            Item(
                images = listOf(
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1")
                ),
                title = "Mochila para niño",
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
                title = "Gorra Goorin Bros",
                description = "Se intercambia iphone por   dmfjsnfdsnmfdsf sdnfdsfnsdfdfns d sfnsdnfdsnfn c ...",
                additionalInfo = "Intercambio",
                location = "Cali",
                isFavorite = false
            ),
            Item(
                images = listOf(
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1")
                ),
                title = "Mause inalambrico",
                description = "Se intercambia iphone por   dmfjsnfdsnmfdsf sdnfdsfnsdfdfns d sfnsdnfdsnfn c ...",
                additionalInfo = "Intercambio",
                location = "Cali",
                isFavorite = false
            ),
            Item(
                images = listOf(
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1")
                ),
                title = "Estuche para iphone 12 pro max",
                description = "Se intercambia iphone por   dmfjsnfdsnmfdsf sdnfdsfnsdfdfns d sfnsdnfdsnfn c ...",
                additionalInfo = "Intercambio",
                location = "Medellin",
                isFavorite = false
            ),

            Item(
                images = listOf(
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1"),
                    Uri.parse("android.resource://com.example.givinglandv1/drawable/producto1")
                ),
                title = "Camiseta",
                description = "Se intercambia iphone por ...",
                additionalInfo = "Donacion",
                location = "Bogota",
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
