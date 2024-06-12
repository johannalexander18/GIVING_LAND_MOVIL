package com.example.givinglandv1

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val images: List<Uri>,
    val title: String,
    val description: String,
    val additionalInfo: String,
    val location: String,
    var isFavorite: Boolean
) : Parcelable