package com.shamlou.domain.repository

import com.shamlou.bases.dataStructure.RadixTree
import com.shamlou.domain.model.cities.ResponseCityDomain
import kotlinx.coroutines.flow.Flow

/**
 * used to invert domain(higher level module) dependency to data(lower level module)
 */
interface CitiesRepository {

    fun getAllCities(): Flow<RadixTree<ResponseCityDomain>>
}