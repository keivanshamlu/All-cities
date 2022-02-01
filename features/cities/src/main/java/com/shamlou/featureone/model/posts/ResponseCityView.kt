package com.shamlou.featureone.model.posts


class ResponseCityView (
    val country: String,
    val name: String,
    val _id: Long,
    val coord: ResponseCityCoordView,
    val title :String = "$name - $country",
    val desc :String = "${coord.lat} - ${coord.lon}",
)

class ResponseCityCoordView (
    val lon: Double,
    val lat: Double
)