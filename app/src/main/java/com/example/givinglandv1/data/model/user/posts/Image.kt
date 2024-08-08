package com.example.givinglandv1.data.model.user.posts

data class Image(
    val id: Int,
    val url: String,
    val imageable_id: Int,
    val imageable_type: String,
    val created_at: String,
    val updated_at: String
)