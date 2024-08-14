package com.example.givinglandv1.ui.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.givinglandv1.data.api.RetrofitInstance
import com.example.givinglandv1.data.model.user.ProfileResponse
import com.example.givinglandv1.data.model.user.User
import com.example.givinglandv1.data.model.user.posts.Post
import kotlinx.coroutines.launch
import retrofit2.HttpException


class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    private val _profile = MutableLiveData<ProfileResponse?>()
    val profile: LiveData<ProfileResponse?> = _profile

    private val _userPosts = MutableLiveData<List<Post>?>()
    val userPosts: LiveData<List<Post>?> = _userPosts


    private val _deletePostResult = MutableLiveData<Boolean>()
    val deletePostResult: LiveData<Boolean> = _deletePostResult

    private val _updatePostResult = MutableLiveData<Boolean>()
    val updatePostResult: LiveData<Boolean> = _updatePostResult

    fun getUser(token: String): LiveData<User?> {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getUser("Bearer $token")
                if (response.isSuccessful) {
                    _user.postValue(response.body())
                } else {
                    _user.postValue(null)
                }
            } catch (e: HttpException) {
                _user.postValue(null)
            }
        }
        return user
    }

    fun getProfile(userId: Int): LiveData<ProfileResponse?> {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProfile(userId)
                if (response.isSuccessful) {
                    _profile.postValue(response.body())
                } else {
                    _profile.postValue(null)
                }
            } catch (e: HttpException) {
                _profile.postValue(null)
            }
        }
        return profile
    }

    fun getUserPosts(userId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getUserPosts(userId)
                if (response.isSuccessful) {
                    _userPosts.postValue(response.body()?.posts)
                } else {
                    _userPosts.postValue(null)
                }
            } catch (e: Exception) {
                _userPosts.postValue(null)
            }
        }
    }


    fun deletePost(token: String, postId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.deletePost("Bearer $token", postId)
                _deletePostResult.postValue(response.isSuccessful)
            } catch (e: Exception) {
                _deletePostResult.postValue(false)
            }
        }
    }

    fun updatePost(token: String, postId: Int, name: String, description: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.updatePost("Bearer $token", postId, name, description)
                _updatePostResult.postValue(response.isSuccessful)
            } catch (e: Exception) {
                _updatePostResult.postValue(false)
            }
        }
    }
}