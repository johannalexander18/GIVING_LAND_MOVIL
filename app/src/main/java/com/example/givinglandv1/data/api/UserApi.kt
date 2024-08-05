package com.example.givinglandv1.data.api

import com.example.givinglandv1.data.model.login.LoginRequest
import com.example.givinglandv1.data.model.login.LoginResponse
import com.example.givinglandv1.data.model.register.RegisterRequest
import com.example.givinglandv1.data.model.register.RegisterResponse
import com.example.givinglandv1.data.model.user.User
import com.example.givinglandv1.data.model.posts.Post
import com.example.givinglandv1.data.model.user.ProfileResponse
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @GET("user")
    suspend fun getUser(@Header("Authorization") authToken: String): Response<User>

    @GET("profiles/{id}")
    suspend fun getProfile(@Path("id") id: Int, @Query("included") included: String = "image"): Response<ProfileResponse>

    @GET("logout")
    suspend fun logout(@Header("Authorization") authToken: String): Response<Void>

    @GET("posts")
    suspend fun getPosts(@Query("included") included: String = "images"): Response<List<Post>>

}