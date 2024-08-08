package com.example.givinglandv1.data.repository

import com.example.givinglandv1.data.api.UserApi
import com.example.givinglandv1.data.model.posts.Category
import com.example.givinglandv1.data.model.posts.Location

class CategoryRepository(private val api: UserApi) {

    suspend fun getCategories(): List<Category>? {
        return try {
            val response = api.getCategories()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}