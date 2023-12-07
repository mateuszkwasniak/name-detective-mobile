package com.example.namedetective.data.api

data class CountryData(
    val country_id: String,
    val probability: Float
)

data class NameData(
    val count: Float,
    val name: String,
    val country: Array<CountryData>
)