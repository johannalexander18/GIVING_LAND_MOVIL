package com.example.givinglandv1.ui.register

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.givinglandv1.data.api.RetrofitInstance
import com.example.givinglandv1.data.model.register.RegisterRequest
import com.example.givinglandv1.data.model.register.RegisterResult
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    fun register(name: String, email: String, password: String, passwordConfirmation: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.users(
                    RegisterRequest(name, email, password, passwordConfirmation)
                )
                if (response.isSuccessful) {
                    val authToken = response.body()?.auth_token
                    if (!authToken.isNullOrEmpty()) {
                        saveToken(authToken)
                        verifyToken(authToken)
                    }
                } else {
                    _registerResult.postValue(RegisterResult(success = false))
                }
            } catch (e: HttpException) {
                _registerResult.postValue(RegisterResult(success = false))
            }
        }
    }

    private suspend fun verifyToken(token: String) {
        try {
            val response = RetrofitInstance.api.getUser("Bearer $token")
            if (response.isSuccessful) {
                _registerResult.postValue(RegisterResult(success = true))
            } else {
                _registerResult.postValue(RegisterResult(success = false))
            }
        } catch (e: HttpException) {
            _registerResult.postValue(RegisterResult(success = false))
        }
    }

    private fun saveToken(token: String) {
        val sharedPref = getApplication<Application>().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        sharedPref.edit().putString("auth_token", token).apply()
    }
}
