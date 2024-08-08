package com.example.givinglandv1.data.repository

import android.content.Context
import java.io.File
import android.net.Uri
import android.util.Log
import com.example.givinglandv1.data.api.UserApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.FileNotFoundException
import java.io.IOException

class PublicarRepository(private val api: UserApi, private val context: Context) {

    suspend fun createPost(
        name: String,
        purpose: String,
        expectedItem: String?,
        description: String,
        locationId: Int,
        categoryId: Int,
        imageUris: List<Uri>,
        authToken: String
    ): Boolean {
        val namePart = name.toRequestBody("text/plain".toMediaTypeOrNull())
        val purposePart = purpose.toRequestBody("text/plain".toMediaTypeOrNull())
        val expectedItemPart = expectedItem?.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionPart = description.toRequestBody("text/plain".toMediaTypeOrNull())
        val locationIdPart = locationId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val categoryIdPart = categoryId.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        val imageParts = mutableListOf<MultipartBody.Part>()
        for (uri in imageUris) {
            val file = getFileFromUri(uri)
            if (file == null || !file.exists()) {
                Log.e("PublicarRepository", "Image file does not exist: ${uri.path}")
                continue
            }
            try {
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                val imagePart = MultipartBody.Part.createFormData("images[]", file.name, requestFile)
                imageParts.add(imagePart)
            } catch (e: FileNotFoundException) {
                Log.e("PublicarRepository", "File not found: ${e.message}")
                // Manejar la excepción de archivo no encontrado
            } catch (e: IOException) {
                Log.e("PublicarRepository", "IO Exception: ${e.message}")
                // Manejar excepciones de entrada/salida
            }
        }

        return try {
            val response = api.createPost(
                "Bearer $authToken",
                namePart,
                purposePart,
                expectedItemPart,
                descriptionPart,
                locationIdPart,
                categoryIdPart,
                imageParts
            )
            response.isSuccessful
        } catch (e: HttpException) {
            Log.e("PublicarRepository", "HTTP Exception: ${e.message}")
            false
        } catch (e: IOException) {
            Log.e("PublicarRepository", "IO Exception: ${e.message}")
            false
        }
    }

    private fun getFileFromUri(uri: Uri): File? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            val tempFile = File.createTempFile("temp", ".jpg", context.cacheDir)
            tempFile.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }
            tempFile
        } catch (e: IOException) {
            Log.e("PublicarRepository", "Error creating file from URI: ${e.message}")
            null
        }
    }
}