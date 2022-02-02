package com.shamlou.data.repoImpl

import com.shamlou.bases.dataStructure.RadixTree
import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.readWrite.Readable
import com.shamlou.bases.readWrite.Writable
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.data.utility.Fakes.emptyRadixTree
import com.shamlou.data.utility.Fakes.validAllCityData
import com.shamlou.data.utility.Fakes.validCitiyListResponseData
import com.shamlou.data_local.utility.MainCoroutineRule
import com.shamlou.domain.model.cities.ResponseAllCitiesDomain
import com.shamlou.domain.model.cities.ResponseCityDomain
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CitiesRepositoryImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repo : CitiesRepositoryImpl

    @MockK(relaxed = true)
    lateinit var citiesLocalDataSourceReadable: Readable<Unit, List<ResponseCityData>>
    @MockK(relaxed = true)
    lateinit var citiesMemoryDataSourceWritable: Writable<List<ResponseCityData>, Unit>
    @MockK(relaxed = true)
    lateinit var citiesMemoryDataSourceReadable: Readable<Unit, RadixTree<ResponseCityDomain>>
    @MockK(relaxed = true)
    lateinit var mapperAllCitiesDataToDomain: Mapper<List<ResponseCityData>, ResponseAllCitiesDomain>

    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        repo = CitiesRepositoryImpl(citiesLocalDataSourceReadable, citiesMemoryDataSourceWritable, citiesMemoryDataSourceReadable, mapperAllCitiesDataToDomain)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getAndSaveAllCities_shouldReadFromLocalDataSource() = mainCoroutineRule.runBlockingTest {

        //given
        stubEveryThingWithValidFakes()
        //when
        val result = repo.getAndSaveAllCities().first()
        //then
        coVerify { citiesLocalDataSourceReadable.read(Unit) }
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getAndSaveAllCities_shouldWriteToMemoryDataSource() = mainCoroutineRule.runBlockingTest {

        //given
        stubEveryThingWithValidFakes()
        coEvery { citiesLocalDataSourceReadable.read(Unit) } returns validCitiyListResponseData
        //when
        val result = repo.getAndSaveAllCities().first()
        //then
        coVerify { citiesMemoryDataSourceWritable.write(validCitiyListResponseData) }
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getAndSaveAllCities_shouldMapData() = mainCoroutineRule.runBlockingTest {

        //given
        stubEveryThingWithValidFakes()
        coEvery { citiesLocalDataSourceReadable.read(Unit) } returns validCitiyListResponseData
        //when
        val result = repo.getAndSaveAllCities().first()
        //then
        verify { mapperAllCitiesDataToDomain.map(validCitiyListResponseData) }
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getAndSaveAllCities_shouldReturnValidData() = mainCoroutineRule.runBlockingTest {

        //given
        stubEveryThingWithValidFakes()
        coEvery { citiesLocalDataSourceReadable.read(Unit) } returns validCitiyListResponseData
        //when
        val result = repo.getAndSaveAllCities().first()
        //then
        verify { mapperAllCitiesDataToDomain.map(validCitiyListResponseData) }
        Assert.assertEquals(result , validAllCityData)
    }
    @Test
    @ExperimentalCoroutinesApi
    fun citiesMemoryDataSourceReadable_shouldCallMemoryDataSource() = mainCoroutineRule.runBlockingTest {

        //given
        stubEveryThingWithValidFakes()
        //when
        val result = repo.getCityDomainRadixTree().first()
        //then
        coVerify { citiesMemoryDataSourceReadable.read(Unit) }
    }
    @Test
    @ExperimentalCoroutinesApi
    fun citiesMemoryDataSourceReadable_shouldReturnValidData() = mainCoroutineRule.runBlockingTest {

        //given
        stubEveryThingWithValidFakes()
        //when
        val result = repo.getCityDomainRadixTree().first()
        //then
        Assert.assertEquals(result , emptyRadixTree)
    }

    private fun stubEveryThingWithValidFakes(){

        // relaxed does not work on generic types
        // so i stub the mapper with valid output
        every { mapperAllCitiesDataToDomain.map(validCitiyListResponseData) } returns validAllCityData
        coEvery { citiesLocalDataSourceReadable.read(Unit) } returns validCitiyListResponseData
        coEvery { citiesMemoryDataSourceWritable.write(validCitiyListResponseData) } returns Unit
        coEvery { citiesMemoryDataSourceReadable.read(Unit) } returns emptyRadixTree
    }
}