package com.shamlou.data.mappers

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.domain.model.cities.ResponseCityCoordDomain
import com.shamlou.domain.model.cities.ResponseCityDomain

class MapperCitiesDataToDomain : Mapper<ResponseCityData, ResponseCityDomain> {
    override fun map(first: ResponseCityData): ResponseCityDomain = first.run { ResponseCityDomain(country , name , _id , coord.let { ResponseCityCoordDomain(it.lon , it.lat ) }) }
}