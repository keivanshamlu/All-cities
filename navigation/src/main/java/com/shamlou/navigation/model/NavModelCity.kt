package com.shamlou.navigation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class NavModelCity(
    val country: String,
    val name: String,
    val _id: Long,
    val coord: NavModelCityCoordView,
    val title :String = "$name - $country",
    val desc :String = "${coord.lat} - ${coord.lon}",
) : Parcelable

@Parcelize
class NavModelCityCoordView (
    val lon: Double,
    val lat: Double
): Parcelable