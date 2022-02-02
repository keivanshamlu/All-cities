package com.shamlou.data.dataSource

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.data.utility.Fakes.validCitiyListResponseData
import com.shamlou.data.utility.Fakes.validCitiyListResponseDomain
import com.shamlou.data_local.utility.MainCoroutineRule
import com.shamlou.domain.model.cities.ResponseCityDomain
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CitiesMemoryDataSourceUnitTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var dataSource : CitiesMemoryDataSource

    @MockK
    lateinit var mapper : Mapper<ResponseCityData, ResponseCityDomain>

    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        dataSource = CitiesMemoryDataSource(mapper)
    }

    //white box tests
    @Test
    @ExperimentalCoroutinesApi
    fun write_shouldMapItemsToDomain() = mainCoroutineRule.runBlockingTest {

        //given
        stubEveryThingWithValidFakes()
        //when
        dataSource.write(validCitiyListResponseData)
        //then
        verifyOrder {
            validCitiyListResponseData.map {
                mapper.map(it)
            }
        }
    }
    //blackbox
    @Test
    @ExperimentalCoroutinesApi
    fun write_shouldMapItemsAndInsertItToRadixTree() = mainCoroutineRule.runBlockingTest {

        //given
        stubEveryThingWithValidFakes()
        //when
        dataSource.write(validCitiyListResponseData)
        val result = dataSource.read(Unit)
        //then
        Assert.assertEquals(result.isEmpty() , false)
    }

    private fun stubEveryThingWithValidFakes(){

        // relaxed does not work on generic types
        // so i stub the mapper with valid output
        validCitiyListResponseData.mapIndexed { index, item ->
            every { mapper.map(item) } returns validCitiyListResponseDomain[index]
        }
    }
}