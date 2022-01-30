package com.shamlou.data.repoImpl

import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.readWrite.Readable
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.domain.repository.CitiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CitiesRepositoryImpl(
    private val citiesLocalDataSource: Readable<Unit, List<ResponseCityData>>,
    private val mapperCitiesDataToDomain: Mapper<ResponseCityData, ResponseCityDomain>
) : CitiesRepository {


    override fun getAllCities(): Flow<List<ResponseCityDomain>> {

        return flow {
            citiesLocalDataSource.read(Unit).map {
                mapperCitiesDataToDomain.map(it)
            }.also {
                emit(it)
            }
        }
    }
}