package com.shamlou.domain

import com.shamlou.bases.useCase.Resource
import com.shamlou.data_local.utility.MainCoroutineRule
import com.shamlou.domain.repository.CitiesRepository
import com.shamlou.domain.useCases.GetAllCitiesUseCase
import com.shamlou.domain.utility.Fakes.sampleError
import com.shamlou.domain.utility.Fakes.validAllCityData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetAllCitiesUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var useCase : GetAllCitiesUseCase

    @MockK
    lateinit var repository: CitiesRepository

    @Before
    fun setUp(){

        MockKAnnotations.init(this)
        useCase = GetAllCitiesUseCase(repository)
    }
    @Test
    @ExperimentalCoroutinesApi
    fun execute_shouldCallGetAllCitiesAndReturnCitiesAsResources_whenSuccessful() {

        runBlocking {
            //given
            coEvery { repository.getAndSaveAllCities() } returns flow { emit(validAllCityData) }
            //when
            val response = useCase.invoke(Unit).toList()
            //then
            coEvery { repository.getAndSaveAllCities() }
            Assert.assertEquals(response.first(), Resource.loading(null))
            Assert.assertEquals(response[1], Resource.success(validAllCityData))
        }
    }
    @Test
    @ExperimentalCoroutinesApi
    fun execute_shouldCallGetAllPostsAndReturnErrorAsResources_whenErrorThrows() {

        runBlocking {
            //given
            coEvery { repository.getAndSaveAllCities() } throws sampleError
            //when
            val response = useCase.invoke(Unit).toList()
            //then
            coEvery { repository.getAndSaveAllCities() }
            Assert.assertEquals(response.first(), Resource.error(sampleError , null))
        }
    }
}