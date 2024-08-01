package com.example.givinglandv1.ui.login

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.givinglandv1.data.api.RetrofitInstance
import com.example.givinglandv1.data.model.LoginRequest
import com.example.givinglandv1.data.model.LoginResponse
import com.example.givinglandv1.data.model.LoginResult
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response


class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.login(LoginRequest(email, password))
                if (response.isSuccessful) {
                    val authToken = response.body()?.auth_token
                    if (!authToken.isNullOrEmpty()) {
                        saveToken(authToken)
                        verifyToken(authToken)
                    }
                } else {
                    _loginResult.postValue(LoginResult(success = false))
                }
            } catch (e: HttpException) {
                _loginResult.postValue(LoginResult(success = false))
            }
        }
    }

    private suspend fun verifyToken(token: String) {
        try {
            val response = RetrofitInstance.api.getUser("Bearer $token")
            if (response.isSuccessful) {
                _loginResult.postValue(LoginResult(success = true))
            } else {
                _loginResult.postValue(LoginResult(success = false))
            }
        } catch (e: HttpException) {
            _loginResult.postValue(LoginResult(success = false))
        }
    }

    private fun saveToken(token: String) {
        val sharedPref = getApplication<Application>().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPref.edit().putString("auth_token", token).apply()
    }
}