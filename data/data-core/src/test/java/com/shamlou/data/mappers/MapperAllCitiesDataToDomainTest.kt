package com.shamlou.data.mappers

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.domain.utility.Fakes.validCitiyListResponseData
import com.shamlou.domain.utility.Fakes.validListsSize
import com.shamlou.data_local.utility.MainCoroutineRule
import com.shamlou.domain.model.cities.ResponseAllCitiesDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


class MapperAllCitiesDataToDomainTest(){

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mapper : Mapper<List<ResponseCityData>, ResponseAllCitiesDomain> = MapperAllCitiesDataToDomain()

    @Test
    @ExperimentalCoroutinesApi
    fun map_shouldReturnCompatibleResponseWhenValidInput() = mainCoroutineRule.runBlockingTest {
        //when
        val response = mapper.map(validCitiyListResponseData)
        //then
        Assert.assertEquals( response, ResponseAllCitiesDomain(validListsSize))
    }
}