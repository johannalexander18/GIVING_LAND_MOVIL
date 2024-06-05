package com.example.givinglandv1

data class Item (
    val imageResId: Int,
    val title: String,
    val description: String,
    val additionalInfo: String,
    val location: String,
    var isFavorite: Boolean
)