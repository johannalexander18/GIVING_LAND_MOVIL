package com.example.givinglandv1.data.model.user.delate

data class DeleteAccountRequest(
    val password: String,
    val password_confirmation: String
)