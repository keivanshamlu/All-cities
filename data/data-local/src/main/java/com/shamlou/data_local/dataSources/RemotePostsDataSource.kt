package com.shamlou.data_local.dataSources

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.data_local.model.cities.ResponseCitiesLocal
import com.shamlou.bases.readWrite.Readable

class CitiesFileDataSource(
    private val mapperCitiesDataToLocal: Mapper<ResponseCitiesLocal, ResponseCityData>
) : Readable<Unit, ResponseCityData> {
    override suspend fun read(input: Unit): ResponseCityData {

        return mapperCitiesDataToLocal.map(ResponseCitiesLocal())
    }
}