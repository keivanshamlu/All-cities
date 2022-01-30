package com.shamlou.featureone.mappers

import com.shamlou.bases.mapper.Mapper
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.featureone.model.posts.ResponseCityCoordView
import com.shamlou.featureone.model.posts.ResponseCityView


class MapperCitiesDomainToView : Mapper<ResponseCityDomain, ResponseCityView> {
    override fun map(first: ResponseCityDomain): ResponseCityView = first.run { ResponseCityView(country , name , _id , coord.let { ResponseCityCoordView(it.lon , it.lat ) }) }
}