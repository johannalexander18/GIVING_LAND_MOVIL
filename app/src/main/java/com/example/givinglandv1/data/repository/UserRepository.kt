package com.example.givinglandv1.data.repository

import com.example.givinglandv1.data.api.UserApi
import com.example.givinglandv1.data.model.user.delate.DeleteAccountRequest
import com.example.givinglandv1.data.model.user.delate.DeleteAccountResponse
import com.example.givinglandv1.util.SharedPrefs
import com.google.gson.Gson
class UserRepository(private val api: UserApi, private val sharedPrefs: SharedPrefs) {
    // ... otros métodos ...

    suspend fun deleteAccount(password: String): Result<String> {
        return try {
            val response = api.deleteAccount(
                "Bearer ${sharedPrefs.authToken}",
                password,
                password // La confirmación es la misma contraseña
            )
            if (response.isSuccessful) {
                sharedPrefs.clearAuthToken() // Elimina el token
                Result.success(response.body()?.message ?: "Cuenta eliminada con éxito")
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    Gson().fromJson(errorBody, DeleteAccountResponse::class.java).message
                } catch (e: Exception) {
                    "Error desconocido"
                }
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
