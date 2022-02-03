package com.shamlou.featureone

import com.shamlou.domain.model.cities.ResponseAllCitiesDomain
import com.shamlou.domain.model.cities.ResponseCityCoordDomain
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.featureone.model.posts.ResponseAllCitiesView
import com.shamlou.featureone.model.posts.ResponseCityCoordView
import com.shamlou.featureone.model.posts.ResponseCityView
import com.shamlou.navigation.model.NavModelCity
import com.shamlou.navigation.model.NavModelCityCoordView

object Fakes {


    val sampleErrorText = "sampleErrorText"
    val sampleError = Exception(sampleErrorText)
    val validCitiyListResponseDomain = listOf(
        ResponseCityDomain("UA","Hurzuf",707860, ResponseCityCoordDomain(34.283333 , 44.549999)),
        ResponseCityDomain("RU","Novinki",519188, ResponseCityCoordDomain(37.666668 , 55.683334)),
        ResponseCityDomain("NP","Gorkhā",1283378, ResponseCityCoordDomain(84.633331 , 28.00)),
        ResponseCityDomain("IN","State of Haryāna",1270260, ResponseCityCoordDomain(76.00 , 29.00))
    )
    val validCitiyListResponseView = listOf(
        ResponseCityView("UA","Hurzuf",707860, ResponseCityCoordView(34.283333 , 44.549999)),
        ResponseCityView("RU","Novinki",519188, ResponseCityCoordView(37.666668 , 55.683334)),
        ResponseCityView("NP","Gorkhā",1283378, ResponseCityCoordView(84.633331 , 28.00)),
        ResponseCityView("IN","State of Haryāna",1270260, ResponseCityCoordView(76.00 , 29.00))
    )
    val validCitiyListResponseNavModel = listOf(
        NavModelCity("UA","Hurzuf",707860, NavModelCityCoordView(34.283333 , 44.549999)),
        NavModelCity("RU","Novinki",519188, NavModelCityCoordView(37.666668 , 55.683334)),
        NavModelCity("NP","Gorkhā",1283378, NavModelCityCoordView(84.633331 , 28.00)),
        NavModelCity("IN","State of Haryāna",1270260, NavModelCityCoordView(76.00 , 29.00))
    )

    val validListsSize = 4
    val validAllCityDomain = ResponseAllCitiesDomain(validListsSize)
    val validAllCityView = ResponseAllCitiesView(validListsSize)

    val validCitiyResponseJson = """{"country":"IN","name":"State of Haryāna","_id":1270260,"coord":{"lon":76,"lat":29}}"""
    val validCitiyResponseDomain = ResponseCityDomain("IN","State of Haryāna",1270260, ResponseCityCoordDomain(76.00 , 29.00))
    val validCitiyResponseView = ResponseCityView("IN","State of Haryāna",1270260, ResponseCityCoordView(76.00 , 29.00))
}