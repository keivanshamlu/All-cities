package com.shamlou.data_local.dataSources

import com.google.gson.Gson
import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.data_local.fileReader.ReadFileFromAssets
import com.shamlou.data_local.model.cities.ResponseCityLocal
import com.shamlou.data_local.utility.Fakes.sampleErrorText
import com.shamlou.data_local.utility.Fakes.type
import com.shamlou.data_local.utility.Fakes.validCitiyListResponseData
import com.shamlou.data_local.utility.Fakes.validCitiyListResponseJson
import com.shamlou.data_local.utility.Fakes.validCitiyListResponseLocal
import com.shamlou.data_local.utility.MainCoroutineRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CitiesFileDataSourceTest{

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    lateinit var dataSource: CitiesFileDataSource

    @MockK(relaxed = true)
    lateinit var mapperCityLocalToData: Mapper<ResponseCityLocal, ResponseCityData>
    @MockK
    lateinit var fileReader : ReadFileFromAssets
    @MockK
    lateinit var gson: Gson
    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        dataSource = CitiesFileDataSource(mapperCityLocalToData, fileReader, gson)
    }

    @Test
    fun read_shouldCallFileReader() = mainCoroutineRule.runBlockingTest {
        // given
        stubEveryThingWithValidFakes()
        //when
        dataSource.read(Unit)
        //then
        verify { fileReader.readFile(any()) }
    }

    @Test
    fun read_shouldGetJsonFromReaderAndCallGson() = mainCoroutineRule.runBlockingTest {
        // given
        stubEveryThingWithValidFakes()
        //when
        dataSource.read(Unit)
        //then
        verify { gson.fromJson<List<ResponseCityLocal>?>(eq(validCitiyListResponseJson) , eq(type)) }
    }
    @Test
    fun read_shouldMapAllTheLocalObjects() = mainCoroutineRule.runBlockingTest {
        // given
        stubEveryThingWithValidFakes()
        //when
        dataSource.read(Unit)
        //then
        verifyOrder {
            validCitiyListResponseLocal.map {
                 mapperCityLocalToData.map(it)
            }
        }
    }
    @Test
    fun read_shouldNotCallMapper_whenJsonIsEmpty() = mainCoroutineRule.runBlockingTest {
        // given
        stubEveryThingWithEmptyResult()
        //when
        dataSource.read(Unit)
        //then
        verify { mapperCityLocalToData.map(any()) wasNot Called}
    }
    @Test
    fun read_shouldReturnValidOutput() = mainCoroutineRule.runBlockingTest {
        // given
        stubEveryThingWithValidFakes()
        //when
        val result = dataSource.read(Unit)
        //then
        Assert.assertEquals(result , validCitiyListResponseData)
    }
    @Test
    fun read_shouldReturnEmptyList_whenJsonIsEmpty() = mainCoroutineRule.runBlockingTest {
        // given
        stubEveryThingWithEmptyResult()
        //when
        val result = dataSource.read(Unit)
        //then
        Assert.assertEquals(result , listOf<ResponseCityData>())
    }
    @Test
    fun read_shouldThrow_whenGsonThrow() = mainCoroutineRule.runBlockingTest {
        // given
        stubEveryThingWithEmptyResult()
        every { gson.fromJson<List<ResponseCityLocal>?>(validCitiyListResponseJson, type) } throws Exception(sampleErrorText)

        // i had to do this instead of assertThrow
        // since it does not support suspend call
        try {
            //when
            dataSource.read(Unit)
        }catch (e : Exception){
            //then
            assertEquals(e, Exception(sampleErrorText))
        }
    }

    private fun stubEveryThingWithValidFakes(){

        // relaxed does not work on generic types
        // so i stub the mapper with valid output
        validCitiyListResponseLocal.mapIndexed { index, item ->
            every { mapperCityLocalToData.map(item) } returns validCitiyListResponseData[index]
        }

        // make gson return valid local model list
        // associated with valid data model list
        every { gson.fromJson<List<ResponseCityLocal>?>(validCitiyListResponseJson, type) } returns validCitiyListResponseLocal

        // make read file return valid json string
        // associated with data and local models
        every { fileReader.readFile(any()) } returns validCitiyListResponseJson
    }
    private fun stubEveryThingWithEmptyResult(){

        // relaxed does not work on generic types
        // so i stub the mapper with valid output
        validCitiyListResponseLocal.mapIndexed { index, item ->
            every { mapperCityLocalToData.map(item) } returns validCitiyListResponseData[index]
        }

        // make gson return empty
        every { gson.fromJson<List<ResponseCityLocal>?>("", type) } returns listOf()

        // make read file return empty json string
        every { fileReader.readFile(any()) } returns ""
    }
}

