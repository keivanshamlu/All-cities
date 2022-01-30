package com.shamlou.domain.model.cities

class ResponseCityDomain (
    val country: String,
    val name: String,
    val _id: Long,
    val coord: ResponseCityCoordDomain
    )

class ResponseCityCoordDomain (
    val lon: Double,
    val lat: Double
)