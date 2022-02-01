package com.shamlou.featureone.mappers

import com.shamlou.bases.mapper.Mapper
import com.shamlou.domain.model.cities.ResponseAllCitiesDomain
import com.shamlou.featureone.model.posts.ResponseAllCitiesView

class MapperAllCitiesDomainToView : Mapper<ResponseAllCitiesDomain, ResponseAllCitiesView> {
    override fun map(first: ResponseAllCitiesDomain): ResponseAllCitiesView = first.run { ResponseAllCitiesView(size) }
}