package com.shamlou.data.mappers

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.data.utility.Fakes
import com.shamlou.data.utility.Fakes.validCitiyResponseDomain
import com.shamlou.data_local.utility.MainCoroutineRule
import com.shamlou.domain.model.cities.ResponseCityDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


class MapperCitiesDataToDomainTest(){

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mapper : Mapper<ResponseCityData, ResponseCityDomain> = MapperCitiesDataToDomain()

    @Test
    @ExperimentalCoroutinesApi
    fun map_shouldReturnCompatibleResponseWhenValidInput() = mainCoroutineRule.runBlockingTest {
        //when
        val response = mapper.map(Fakes.validCitiyResponseData)
        //then
        Assert.assertEquals(response._id, validCitiyResponseDomain._id)
        Assert.assertEquals(response.name, validCitiyResponseDomain.name)
        Assert.assertEquals(response.country, validCitiyResponseDomain.country)
    }
}