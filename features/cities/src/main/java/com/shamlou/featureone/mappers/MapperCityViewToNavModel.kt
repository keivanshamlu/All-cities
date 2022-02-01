package com.shamlou.featureone.mappers

import com.shamlou.bases.mapper.Mapper
import com.shamlou.featureone.model.posts.ResponseCityView
import com.shamlou.navigation.model.NavModelCity
import com.shamlou.navigation.model.NavModelCityCoordView

class MapperCityViewToNavModel : Mapper<ResponseCityView, NavModelCity> {
    override fun map(first: ResponseCityView): NavModelCity = first.run { NavModelCity(country , name , _id , coord.let { NavModelCityCoordView(it.lon , it.lat ) }) }
}