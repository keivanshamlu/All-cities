package com.shamlou.data_local.mappers.cities

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.data_local.model.cities.ResponseCityLocal
import com.shamlou.data_local.utility.Fakes.validCitiyResponseData
import com.shamlou.data_local.utility.Fakes.validCitiyResponseLocal
import com.shamlou.data_local.utility.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MapperCitiesLocalToDataTest(){

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mapper : Mapper<ResponseCityLocal, ResponseCityData> = MapperCitiesLocalToData()

    @Test
    @ExperimentalCoroutinesApi
    fun map_shouldReturnCompatibleResponseWhenValidInput() = mainCoroutineRule.runBlockingTest {
        //when
        val response = mapper.map(validCitiyResponseLocal)
        //then
        Assert.assertEquals(response._id, validCitiyResponseData._id)
    }
}