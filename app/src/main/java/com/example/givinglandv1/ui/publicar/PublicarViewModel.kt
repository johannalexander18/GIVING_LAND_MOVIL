package com.example.givinglandv1.ui.publicar

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.givinglandv1.data.api.RetrofitInstance
import com.example.givinglandv1.data.repository.PublicarRepository
import kotlinx.coroutines.launch

class PublicarViewModel(application: Application) : AndroidViewModel(application) {

    private val context: Context = application.applicationContext
    private val repository = PublicarRepository(RetrofitInstance.api, context)
    private val _postCreationStatus = MutableLiveData<Boolean>()
    val postCreationStatus: LiveData<Boolean> get() = _postCreationStatus

    fun createPost(
        name: String,
        purpose: String,
        expectedItem: String?,
        description: String,
        locationId: Int,
        categoryId: Int,
        imageUris: List<Uri>,
        authToken: String
    ): LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        viewModelScope.launch {
            val result = repository.createPost(
                name,
                purpose,
                expectedItem,
                description,
                locationId,
                categoryId,
                imageUris,
                authToken
            )
            liveData.postValue(result)
        }
        return liveData
    }
}