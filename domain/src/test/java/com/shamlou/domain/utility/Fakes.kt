package com.shamlou.domain.utility

import com.shamlou.bases.dataStructure.RadixTree
import com.shamlou.domain.model.cities.ResponseAllCitiesDomain
import com.shamlou.domain.model.cities.ResponseCityCoordDomain
import com.shamlou.domain.model.cities.ResponseCityDomain

object Fakes {


    val sampleErrorText = "sampleErrorText"
    val sampleError = Exception(sampleErrorText)
    val validCitiyListResponseJson = """[
        {"country":"UA","name":"Hurzuf","_id":707860,"coord":{"lon":34.283333,"lat":44.549999}},
        {"country":"RU","name":"Novinki","_id":519188,"coord":{"lon":37.666668,"lat":55.683334}},
        {"country":"NP","name":"Gorkhā","_id":1283378,"coord":{"lon":84.633331,"lat":28}},
        {"country":"IN","name":"State of Haryāna","_id":1270260,"coord":{"lon":76,"lat":29}}]"""

    val validCitiyListResponseDomain = listOf(
        ResponseCityDomain("UA","Hurzuf",707860, ResponseCityCoordDomain(34.283333 , 44.549999)),
        ResponseCityDomain("RU","Novinki",519188, ResponseCityCoordDomain(37.666668 , 55.683334)),
        ResponseCityDomain("NP","Gorkhā",1283378, ResponseCityCoordDomain(84.633331 , 28.00)),
        ResponseCityDomain("IN","State of Haryāna",1270260, ResponseCityCoordDomain(76.00 , 29.00))
    )

    val validListsSize = 4
    val validAllCityData = ResponseAllCitiesDomain(validListsSize)

    val validCitiyResponseJson = """{"country":"IN","name":"State of Haryāna","_id":1270260,"coord":{"lon":76,"lat":29}}"""
    val validCitiyResponseDomain = ResponseCityDomain("IN","State of Haryāna",1270260, ResponseCityCoordDomain(76.00 , 29.00))

    val emptyRadixTree = RadixTree<ResponseCityDomain>()
}