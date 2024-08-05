package com.example.givinglandv1.data.model.user

data class ProfileResponse(
    val id: Int,
    val google_avatar: String?,
    val user_id: Int,
    val username: String,
    val name: String,
    val email: String,
    val image: Image
)