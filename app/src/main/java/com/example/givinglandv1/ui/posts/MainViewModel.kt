package com.example.givinglandv1.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.givinglandv1.data.api.RetrofitInstance
import com.example.givinglandv1.data.model.posts.Post
import com.example.givinglandv1.data.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class MainViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPosts()
                if (response.isSuccessful && response.body() != null) {
                    _posts.postValue(response.body())
                } else {
                    // Maneja errores
                }
            } catch (e: Exception) {
                // Maneja excepciones
            }
        }
    }
}