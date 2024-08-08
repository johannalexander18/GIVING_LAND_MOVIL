package com.example.givinglandv1.data.model.user.posts

data class UserPostsResponse(
    val id: Int,
    val username: String,
    val name: String,
    val email: String,
    val banned: Int,
    val email_verified_at: String,
    val created_at: String,
    val updated_at: String,
    val posts: List<Post>
)