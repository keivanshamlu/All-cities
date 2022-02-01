package com.shamlou.data.mappers

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.domain.model.cities.ResponseAllCitiesDomain

class MapperAllCitiesDataToDomain : Mapper<List<ResponseCityData>, ResponseAllCitiesDomain> {
    override fun map(first: List<ResponseCityData>): ResponseAllCitiesDomain = first.let { ResponseAllCitiesDomain(it.size) }
}