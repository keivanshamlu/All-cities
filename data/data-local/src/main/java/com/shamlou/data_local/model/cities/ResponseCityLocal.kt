package com.shamlou.data_local.model.cities

import com.google.gson.annotations.SerializedName

class ResponseCityLocal (
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("_id")
    var _id: Long? = null,
    @SerializedName("coord")
    var coord: ResponseCityCoordLocal? = null
    )

class ResponseCityCoordLocal (
    @SerializedName("lon")
    var lon: Double? = null,
    @SerializedName("lat")
    var lat: Double? = null
)