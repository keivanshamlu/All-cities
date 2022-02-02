package com.shamlou.data.dataSource

import com.shamlou.bases.dataStructure.Item
import com.shamlou.bases.dataStructure.RadixTree
import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.readWrite.Readable
import com.shamlou.bases.readWrite.Writable
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.domain.model.cities.ResponseCityDomain

/**
 * holds a RadixTree of City object in memory so it populates
 * it one time and then use populated tree since then
 */
class CitiesMemoryDataSource(
    private val mapperCitiesDataToDomain: Mapper<ResponseCityData, ResponseCityDomain>,
) : Readable<Unit, RadixTree<ResponseCityDomain>>,
    Writable<List<ResponseCityData>, Unit> {

    // since insertion of a list into a radix tree has time
    // complexity of O(n*k) and it is cpu sensitive, we check
    // whether the tree is empty or not(since our data source is
    // static and it never change, it will be alright), so whenever
    // tree is empty we fetch data and insert it all into tree and
    // from that time, we use that populated tree
    private var tree = RadixTree<ResponseCityDomain>()

    override suspend fun read(input: Unit): RadixTree<ResponseCityDomain> {
        return tree
    }

    /**
     * gets a list of data model and then map it to domain
     * and then insert all of them into radixTree
     */
    override suspend fun write(input: List<ResponseCityData>) {
        input.map {
            tree.insert(mapperCitiesDataToDomain.map(it).let { Item(it.name, it) })
        }
    }
}