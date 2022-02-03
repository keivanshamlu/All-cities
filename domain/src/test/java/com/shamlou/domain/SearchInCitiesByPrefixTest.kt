package com.shamlou.domain

import com.shamlou.bases.useCase.Resource
import com.shamlou.data_local.utility.MainCoroutineRule
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.domain.repository.CitiesRepository
import com.shamlou.domain.useCases.SearchInCitiesByPrefix
import com.shamlou.domain.utility.Fakes
import com.shamlou.domain.utility.Fakes.emptyRadixTree
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

class SearchInCitiesByPrefixTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var useCase: SearchInCitiesByPrefix

    @MockK
    lateinit var repository: CitiesRepository

    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        useCase = SearchInCitiesByPrefix(repository)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun execute_shouldCallSearchInCitiesByPrefixAndReturnCitiesAsResources_whenSuccessful() {

        runBlocking {
            //given
            coEvery { repository.getCityDomainRadixTree() } returns flow { emit(emptyRadixTree) }
            //when
            val prefix = "samplePrefix"
            val response = useCase.invoke(prefix).toList()
            //then
            coEvery { repository.getCityDomainRadixTree() }
            Assert.assertEquals(response.first(), Resource.loading(null))
            Assert.assertEquals(response[1], Resource.success(emptyList<ResponseCityDomain>()))
        }
    }

    @Test
    @ExperimentalCoroutinesApi
    fun execute_shouldCallGetAllPostsAndReturnErrorAsResources_whenErrorThrows() {

        runBlocking {
            //given
            coEvery { repository.getCityDomainRadixTree() } throws Fakes.sampleError
            //when
            val prefix = "samplePrefix"
            val response = useCase.invoke(prefix).toList()
            //then
            coEvery { repository.getCityDomainRadixTree() }
            Assert.assertEquals(response.first(), Resource.error(Fakes.sampleError, null))
        }
    }
}