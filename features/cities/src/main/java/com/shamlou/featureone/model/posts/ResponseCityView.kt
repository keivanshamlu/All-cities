package com.shamlou.featureone.model.posts


class ResponseCityView (
    val country: String,
    val name: String,
    val _id: Long,
    val coord: ResponseCityCoordView
)

class ResponseCityCoordView (
    val lon: Double,
    val lat: Double
)