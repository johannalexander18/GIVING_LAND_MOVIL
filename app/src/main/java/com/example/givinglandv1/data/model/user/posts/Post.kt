package com.example.givinglandv1.data.model.user.posts

data class Post(
    val id: Int,
    val name: String,
    val purpose: String,
    val expected_item: String?,
    val description: String,
    val reported: Int,
    val banned: Int,
    val user_id: Int,
    val location_id: Int,
    val category_id: Int,
    val created_at: String,
    val updated_at: String,
    val images: List<Image>
)