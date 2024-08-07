package com.example.givinglandv1.data.model.posts

data class Location(
    val id: Int,
    val region: String,
    val codigo_dane_departamento: String,
    val departamento: String,
    val codigo_dane_municipio: String,
    val municipio: String
)