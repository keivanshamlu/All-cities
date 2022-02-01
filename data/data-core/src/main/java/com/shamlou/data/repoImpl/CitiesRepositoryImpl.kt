package com.shamlou.data.repoImpl

import com.shamlou.bases.dataStructure.Item
import com.shamlou.bases.dataStructure.RadixTree
import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.readWrite.Readable
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.domain.repository.CitiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class CitiesRepositoryImpl(
    private val citiesLocalDataSource: Readable<Unit, List<ResponseCityData>>,
    private val mapperCitiesDataToDomain: Mapper<ResponseCityData, ResponseCityDomain>
) : CitiesRepository {

    var tree = RadixTree<ResponseCityDomain>()
    override fun getAllCities(): Flow<RadixTree<ResponseCityDomain>> {

        return flow {

            try {
                takeIf { tree.isEmpty() }?.apply {
                    citiesLocalDataSource.read(Unit).map {
                        mapperCitiesDataToDomain.map(it).let {
                            tree.insert(Item(it.name, it))
                        }
                    }
                }
                emit(tree)
            }catch (e : Exception){
                print(e.localizedMessage)
            }

        }
    }
}