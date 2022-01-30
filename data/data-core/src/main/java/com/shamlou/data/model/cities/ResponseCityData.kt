package com.shamlou.data.model.cities

class ResponseCityData (
    val country: String,
    val name: String,
    val _id: Long,
    val coord: ResponseCityCoordData
    )

class ResponseCityCoordData (
    val lon: Double,
    val lat: Double
)