package com.example.givinglandv1.data.api

import com.example.givinglandv1.data.model.login.LoginRequest
import com.example.givinglandv1.data.model.login.LoginResponse
import com.example.givinglandv1.data.model.posts.Category
import com.example.givinglandv1.data.model.posts.Location
import com.example.givinglandv1.data.model.register.RegisterRequest
import com.example.givinglandv1.data.model.register.RegisterResponse
import com.example.givinglandv1.data.model.user.User
import com.example.givinglandv1.data.model.posts.Post
import com.example.givinglandv1.data.model.user.ProfileResponse
import com.example.givinglandv1.data.model.user.delate.DeleteAccountRequest
import com.example.givinglandv1.data.model.user.delate.DeleteAccountResponse
import com.example.givinglandv1.data.model.user.posts.UserPostsResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("users")
    suspend fun users(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @GET("user")
    suspend fun getUser(@Header("Authorization") authToken: String): Response<User>

    @GET("profiles/{id}")
    suspend fun getProfile(@Path("id") id: Int, @Query("included") included: String = "image"): Response<ProfileResponse>

    @GET("logout")
    suspend fun logout(@Header("Authorization") authToken: String): Response<Void>

    @GET("posts")
    suspend fun getPosts(@Query("included") included: String = "images"): Response<List<Post>>

    @GET("locations")
    suspend fun getLocations(): Response<List<Location>>

    @GET("categories")
    suspend fun getCategories(): Response<List<Category>>

    @GET("users/{id}")
    suspend fun getUserPosts(
        @Path("id") id: Int,
        @Query("included") included: String = "posts.images"
    ): Response<UserPostsResponse>

    @FormUrlEncoded
    @PUT("posts/{id}")
    suspend fun updatePost(
        @Header("Authorization") authToken: String,
        @Path("id") postId: Int,
        @Field("name") name: String,
        @Field("description") description: String
    ): Response<ResponseBody>

    @DELETE("posts/{id}")
    suspend fun deletePost(
        @Header("Authorization") authToken: String,
        @Path("id") postId: Int
    ): Response<ResponseBody>

    @Multipart
    @POST("posts")
    suspend fun createPost(
        @Header("Authorization") authToken: String,
        @Part("name") name: RequestBody,
        @Part("purpose") purpose: RequestBody,
        @Part("expected_item") expectedItem: RequestBody?,
        @Part("description") description: RequestBody,
        @Part("location_id") locationId: RequestBody,
        @Part("category_id") categoryId: RequestBody,
        @Part images: List<MultipartBody.Part>
    ): Response<ResponseBody>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "user", hasBody = true)
    suspend fun deleteAccount(
        @Header("Authorization") authToken: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String
    ): Response<DeleteAccountResponse>
}