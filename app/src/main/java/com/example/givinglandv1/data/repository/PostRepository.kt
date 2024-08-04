package com.example.givinglandv1.data.repository

import com.example.givinglandv1.data.api.RetrofitInstance
import com.example.givinglandv1.data.model.posts.Post
import retrofit2.Response

class PostRepository {
    suspend fun getPosts(): Response<List<Post>> {
        return RetrofitInstance.api.getPosts()
    }
}