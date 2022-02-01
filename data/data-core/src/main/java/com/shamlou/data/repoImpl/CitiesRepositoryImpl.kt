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

/**
 * an implementation of [CitiesRepository] which is basically
 * focusing all possible ways to get data from input
 */
class CitiesRepositoryImpl(
    private val citiesLocalDataSource: Readable<Unit, List<ResponseCityData>>,
    private val mapperCitiesDataToDomain: Mapper<ResponseCityData, ResponseCityDomain>
) : CitiesRepository {

    // since insertion of a list into a radix tree has time
    // complexity of O(n*k) and it is cpu sensitive, we check
    // whether the tree is empty or not(since our data source is
    // static and it never change, it will be alright), so whenever
    // tree is empty we fetch data and insert it all into tree and
    // from that time, we use that populated tree
    var tree = RadixTree<ResponseCityDomain>()


    /**
     * checks if tree is populated at first
     * if tree is empty, then it calls [citiesLocalDataSource] and gets data and then it will insert it in tree
     * if tree is populated, then it will return the tree
     */
    override fun getAllCities(): Flow<RadixTree<ResponseCityDomain>> {

        return flow {

            takeIf { tree.isEmpty() }?.apply {
                citiesLocalDataSource.read(Unit).map {
                    mapperCitiesDataToDomain.map(it).let {
                        tree.insert(Item(it.name, it))
                    }
                }
            }
            emit(tree)
        }
    }
}