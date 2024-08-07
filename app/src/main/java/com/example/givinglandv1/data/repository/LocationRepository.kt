package com.example.givinglandv1.data.repository

import com.example.givinglandv1.data.api.UserApi
import com.example.givinglandv1.data.model.posts.Location

class LocationRepository(private val api: UserApi) {

    suspend fun getLocations(): List<Location>? {
        val response = api.getLocations()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}