package com.shamlou.data.repoImpl

import com.shamlou.bases.dataStructure.RadixTree
import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.readWrite.Readable
import com.shamlou.bases.readWrite.Writable
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.domain.model.cities.ResponseAllCitiesDomain
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.domain.repository.CitiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * an implementation of [CitiesRepository] which is basically
 * focusing all possible ways to get data from input
 */
class CitiesRepositoryImpl(
    private val citiesLocalDataSourceReadable: Readable<Unit, List<ResponseCityData>>,
    private val citiesMemoryDataSourceWritable: Writable<List<ResponseCityData>, Unit>,
    private val citiesMemoryDataSourceReadable: Readable<Unit, RadixTree<ResponseCityDomain>>,
    private val mapperAllCitiesDataToDomain: Mapper<List<ResponseCityData>, ResponseAllCitiesDomain>,
) : CitiesRepository {

    /**
     * calls [citiesLocalDataSourceReadable] to get all cities by reading the file
     * and then write it in [citiesMemoryDataSourceWritable] to cache it in memory
     * and then map the list to domain model and emits it
     */
    override fun getAndSaveAllCities(): Flow<ResponseAllCitiesDomain> {

        return flow {
            citiesLocalDataSourceReadable.read(Unit).also {
                citiesMemoryDataSourceWritable.write(it)
                emit(mapperAllCitiesDataToDomain.map(it))
            }
        }
    }

    /**
     * emits cached cities in [citiesMemoryDataSourceReadable]
     */
    override fun searchInCitiesByPrefix(): Flow<RadixTree<ResponseCityDomain>> {

        return flow {
            citiesMemoryDataSourceReadable.read(Unit).also {

                emit(it)
            }
        }
    }
}