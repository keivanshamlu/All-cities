package com.shamlou.data_local.mappers.cities

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.model.cities.ResponseCityCoordData
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.data_local.model.cities.ResponseCitiesLocal

class MapperCitiesLocalToData : Mapper<ResponseCitiesLocal, ResponseCityData> {
    override fun map(first: ResponseCitiesLocal): ResponseCityData = first.run { ResponseCityData(country?:"" , name?:"" , _id?:-1 , coord.let { ResponseCityCoordData(it?.lon?:-1.0 , it?.lat?:-1.0 ) }) }
}