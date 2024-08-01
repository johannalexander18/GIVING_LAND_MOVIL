package com.example.givinglandv1.data.api

import com.example.givinglandv1.data.model.LoginRequest
import com.example.givinglandv1.data.model.LoginResponse
import com.example.givinglandv1.data.model.User
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("user")
    suspend fun getUser(@Header("Authorization") authToken: String): Response<User>
}