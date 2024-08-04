package com.example.givinglandv1.data.model.posts
data class Post(
    val id: Int,
    val name: String,
    val purpose: String,
    val description: String,
    val location_id: Int,
    val images: List<Image>?
)
