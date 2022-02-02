package com.shamlou.data_local.utility

import com.google.gson.reflect.TypeToken
import com.shamlou.data.model.cities.ResponseCityCoordData
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.data_local.model.cities.ResponseCityLocal
import com.shamlou.data_local.model.cities.ResponseCityCoordLocal

object Fakes {


    val type = TypeToken.getParameterized(MutableList::class.java, ResponseCityLocal::class.java).type
    val sampleErrorText = "sampleErrorText"
    val validCitiyListResponseJson = """[
        {"country":"UA","name":"Hurzuf","_id":707860,"coord":{"lon":34.283333,"lat":44.549999}},
        {"country":"RU","name":"Novinki","_id":519188,"coord":{"lon":37.666668,"lat":55.683334}},
        {"country":"NP","name":"Gorkhā","_id":1283378,"coord":{"lon":84.633331,"lat":28}},
        {"country":"IN","name":"State of Haryāna","_id":1270260,"coord":{"lon":76,"lat":29}}]"""

    val validCitiyListResponseLocal = listOf(
        ResponseCityLocal("UA","Hurzuf",707860, ResponseCityCoordLocal(34.283333 , 44.549999)),
        ResponseCityLocal("RU","Novinki",519188, ResponseCityCoordLocal(37.666668 , 55.683334)),
        ResponseCityLocal("NP","Gorkhā",1283378, ResponseCityCoordLocal(84.633331 , 28.00)),
        ResponseCityLocal("IN","State of Haryāna",1270260, ResponseCityCoordLocal(76.00 , 29.00))
    )

    val validCitiyListResponseData = listOf(
        ResponseCityData("UA","Hurzuf",707860, ResponseCityCoordData(34.283333 , 44.549999)),
        ResponseCityData("RU","Novinki",519188, ResponseCityCoordData(37.666668 , 55.683334)),
        ResponseCityData("NP","Gorkhā",1283378, ResponseCityCoordData(84.633331 , 28.00)),
        ResponseCityData("IN","State of Haryāna",1270260, ResponseCityCoordData(76.00 , 29.00))
    )

    val validCitiyResponseJson = """{"country":"IN","name":"State of Haryāna","_id":1270260,"coord":{"lon":76,"lat":29}}"""
    val validCitiyResponseLocal = ResponseCityLocal("IN","State of Haryāna",1270260, ResponseCityCoordLocal(76.00 , 29.00))
    val validCitiyResponseData = ResponseCityData("IN","State of Haryāna",1270260, ResponseCityCoordData(76.00 , 29.00))
}