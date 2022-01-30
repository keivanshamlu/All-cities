package com.shamlou.domain.repository

import com.shamlou.domain.model.cities.ResponseCityDomain
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {

    fun getAllCities(): Flow<List<ResponseCityDomain>>
}