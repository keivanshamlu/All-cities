package com.shamlou.featureone

import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.useCase.FlowUseCase
import com.shamlou.bases.useCase.Resource
import com.shamlou.bases.useCase.map
import com.shamlou.bases.useCase.mapListed
import com.shamlou.domain.model.cities.ResponseAllCitiesDomain
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.featureone.Fakes.sampleError
import com.shamlou.featureone.Fakes.validAllCityDomain
import com.shamlou.featureone.Fakes.validAllCityView
import com.shamlou.featureone.Fakes.validCitiyListResponseDomain
import com.shamlou.featureone.Fakes.validCitiyListResponseNavModel
import com.shamlou.featureone.Fakes.validCitiyListResponseView
import com.shamlou.featureone.fakes.*
import com.shamlou.featureone.model.posts.ResponseAllCitiesView
import com.shamlou.featureone.model.posts.ResponseCityView
import com.shamlou.featureone.ui.cities.AllCitiesViewModel
import com.shamlou.navigation.model.NavModelCity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * since mockk does not support stubbing functions in base classes,
 * I couldn't stub execute() in [FlowUseCase] so I had to pick another test double...
 * I picked Faker test double and for every test scenario i implement [FlowUseCase]
 * and then in execute() i return the value that my test scenario needs.
 * you can find Fakers in [FakeAllCitiesUseCaseSuccess] file
 */
class AllCitiesViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel : AllCitiesViewModel

    lateinit var getAllCitiesUseCase : FlowUseCase<Unit, ResponseAllCitiesDomain>
    lateinit var searchInCitiesByPrefix : FlowUseCase<String, List<ResponseCityDomain>>
    @MockK
    lateinit var mapperAllCitiesDomainToView: Mapper<ResponseAllCitiesDomain, ResponseAllCitiesView>
    @MockK
    lateinit var mapperCitiesDomainToView: Mapper<ResponseCityDomain, ResponseCityView>
    @MockK
    lateinit var mapperCitiesViewToNavModel: Mapper<ResponseCityView, NavModelCity>

    @Before
    fun setUp(){

        MockKAnnotations.init(this)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun allCities_shouldFetchSuccess_whenViewModelCreated() = mainCoroutineRule.dispatcher.runBlockingTest {

        //given
        //success fake
        getAllCitiesUseCase = FakeAllCitiesUseCaseSuccess()
        searchInCitiesByPrefix = FakeGetCitiesUseCaseSuccess()
        initMappers()
        //when
        viewModel = AllCitiesViewModel(getAllCitiesUseCase, searchInCitiesByPrefix, mapperAllCitiesDomainToView, mapperCitiesDomainToView, mapperCitiesViewToNavModel)
        //then
        val emitted = getLastEmitted(viewModel.allCities)
        Assert.assertEquals(emitted.status, Resource.Status.SUCCESS)
        Assert.assertEquals(emitted.data, validAllCityView)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun allCities_shouldFetchError_whenViewModelCreatedAndUseCaseThrows() = mainCoroutineRule.dispatcher.runBlockingTest {

        //given
        //success fake
        getAllCitiesUseCase = FakeAllCitiesUseCaseFailure()
        searchInCitiesByPrefix = FakeGetCitiesUseCaseSuccess()
        initMappers()
        //when
        viewModel = AllCitiesViewModel(getAllCitiesUseCase, searchInCitiesByPrefix, mapperAllCitiesDomainToView, mapperCitiesDomainToView, mapperCitiesViewToNavModel)
        //then
        val emitted = getLastEmitted(viewModel.allCities)
        Assert.assertEquals(emitted.status, Resource.Status.ERROR)
        Assert.assertEquals(emitted.error, sampleError)
    }
    @Test
    @ExperimentalCoroutinesApi
    fun search_shouldBeCalledAndFetchSuccessData_whenTextChanged() = mainCoroutineRule.dispatcher.runBlockingTest {

        //given
        //success fake
        getAllCitiesUseCase = FakeAllCitiesUseCaseSuccess()
        searchInCitiesByPrefix = FakeGetCitiesUseCaseSuccess()
        initMappers()
        //when
        viewModel = AllCitiesViewModel(getAllCitiesUseCase, searchInCitiesByPrefix, mapperAllCitiesDomainToView, mapperCitiesDomainToView, mapperCitiesViewToNavModel)
        viewModel.enteredText.value = "teh"
        //then
        val emitted = getLastEmitted(viewModel.filteredCities)
        Assert.assertEquals(emitted.status, Resource.Status.SUCCESS)
        Assert.assertEquals(emitted.data, validCitiyListResponseView)
    }
    @Test
    @ExperimentalCoroutinesApi
    fun search_shouldBeCalledAndFetchErrorData_whenTextChangedAndUseCaseThrows() = mainCoroutineRule.dispatcher.runBlockingTest {

        //given
        //success fake
        getAllCitiesUseCase = FakeAllCitiesUseCaseSuccess()
        searchInCitiesByPrefix = FakeGetCitiesUseCaseFailure()
        initMappers()
        //when
        viewModel = AllCitiesViewModel(getAllCitiesUseCase, searchInCitiesByPrefix, mapperAllCitiesDomainToView, mapperCitiesDomainToView, mapperCitiesViewToNavModel)
        viewModel.enteredText.value = "teh"
        //then
        
        val emitted = getLastEmitted(viewModel.filteredCities)
        Assert.assertEquals(emitted.status, Resource.Status.ERROR)
        Assert.assertEquals(emitted.error, sampleError)
    }
    @Test
    @ExperimentalCoroutinesApi
    fun isEmptyStateButtonVisible_shouldBeTrue_whenViewModelCreatedAndAllCitiesWasSuccessfull() = mainCoroutineRule.dispatcher.runBlockingTest {

        //given
        //success fake
        getAllCitiesUseCase = FakeAllCitiesUseCaseSuccess()
        searchInCitiesByPrefix = FakeGetCitiesUseCaseSuccess()
        initMappers()
        //when
        viewModel = AllCitiesViewModel(getAllCitiesUseCase, searchInCitiesByPrefix, mapperAllCitiesDomainToView, mapperCitiesDomainToView, mapperCitiesViewToNavModel)
        //then
        val emitted = getLastEmitted(viewModel.isEmptyStateButtonVisible)
        Assert.assertEquals(emitted, true)
    }
    @Test
    @ExperimentalCoroutinesApi
    fun isEmptyStateButtonVisible_shouldBeFalse_whenAllCitiesWasSuccessfulAndTextChangeAndFilteredListEmittedSuccessfully() = mainCoroutineRule.dispatcher.runBlockingTest {

        //given
        //success fake
        getAllCitiesUseCase = FakeAllCitiesUseCaseSuccess()
        searchInCitiesByPrefix = FakeGetCitiesUseCaseSuccess()
        initMappers()
        //when
        viewModel = AllCitiesViewModel(getAllCitiesUseCase, searchInCitiesByPrefix, mapperAllCitiesDomainToView, mapperCitiesDomainToView, mapperCitiesViewToNavModel)
        viewModel.enteredText.value = "teh"
        //then
        val emitted = getLastEmitted(viewModel.isEmptyStateButtonVisible)
        Assert.assertEquals(emitted, false)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun descText_shouldBeValid_whenViewModelCreatedAndAllCitiesWasSuccessfull() = mainCoroutineRule.dispatcher.runBlockingTest {

        //given
        //success fake
        getAllCitiesUseCase = FakeAllCitiesUseCaseSuccess()
        searchInCitiesByPrefix = FakeGetCitiesUseCaseSuccess()
        initMappers()
        //when
        viewModel = AllCitiesViewModel(getAllCitiesUseCase, searchInCitiesByPrefix, mapperAllCitiesDomainToView, mapperCitiesDomainToView, mapperCitiesViewToNavModel)
        //then
        
        val emitted = getLastEmitted(viewModel.descText)
        Assert.assertEquals(emitted, "explore 4 cities...")
    }

    @Test
    @ExperimentalCoroutinesApi
    fun descText_shouldBeFalse_whenAllCitiesWasSuccessfulAndTextChangeAndFilteredListEmittedSuccessfully() = mainCoroutineRule.dispatcher.runBlockingTest {

        //given
        //success fake
        getAllCitiesUseCase = FakeAllCitiesUseCaseSuccess()
        searchInCitiesByPrefix = FakeGetCitiesUseCaseSuccess()
        initMappers()
        //when
        viewModel = AllCitiesViewModel(getAllCitiesUseCase, searchInCitiesByPrefix, mapperAllCitiesDomainToView, mapperCitiesDomainToView, mapperCitiesViewToNavModel)
        viewModel.enteredText.value = "teh"
        //then
        val emitted = getLastEmitted(viewModel.descText)
        Assert.assertEquals(emitted, "4 result found")
    }

    /**
     * 
     * this function stubs all mappers with valid data 
     */
    private fun initMappers(){

        every { mapperAllCitiesDomainToView.map(validAllCityDomain) } returns validAllCityView


        validCitiyListResponseDomain.mapIndexed { index, item ->
            every { mapperCitiesDomainToView.map(item) } returns validCitiyListResponseView[index]
        }
        validCitiyListResponseView.mapIndexed { index, item ->
            every { mapperCitiesViewToNavModel.map(item) } returns validCitiyListResponseNavModel[index]
        }
        // mockk way to stub Extension functions
        // (stub map and mapListed function of resource)
        mockkStatic(RESOURCES_PACKAGE)
        every {
            Resource(Resource.Status.SUCCESS, validAllCityDomain, null).map(
                mapperAllCitiesDomainToView
            )
        } returns Resource.success(validAllCityView)

        every {
            Resource(Resource.Status.SUCCESS, validCitiyListResponseDomain, null).mapListed(
                mapperCitiesDomainToView
            )
        } returns Resource.success(validCitiyListResponseView)
    }


}