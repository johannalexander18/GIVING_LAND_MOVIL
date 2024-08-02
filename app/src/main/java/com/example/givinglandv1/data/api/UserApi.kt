package com.example.givinglandv1.data.api

import com.example.givinglandv1.data.model.login.LoginRequest
import com.example.givinglandv1.data.model.login.LoginResponse
import com.example.givinglandv1.data.model.register.RegisterRequest
import com.example.givinglandv1.data.model.register.RegisterResponse
import com.example.givinglandv1.data.model.User
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @GET("user")
    suspend fun getUser(@Header("Authorization") authToken: String): Response<User>


    @GET("logout")
    suspend fun logout(@Header("Authorization") authToken: String): Response<Void>



}