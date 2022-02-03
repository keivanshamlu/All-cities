package com.shamlou.featureone.ui.cities

import androidx.lifecycle.viewModelScope
import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.useCase.FlowUseCase
import com.shamlou.bases.useCase.Resource
import com.shamlou.bases.useCase.map
import com.shamlou.bases.useCase.mapListed
import com.shamlou.bases_android.viewModel.BaseViewModel
import com.shamlou.domain.model.cities.ResponseAllCitiesDomain
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.featureone.model.posts.ResponseAllCitiesView
import com.shamlou.featureone.model.posts.ResponseCityView
import com.shamlou.navigation.command.NavigationFlow
import com.shamlou.navigation.model.NavModelCity
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AllCitiesViewModel(
    private val getAllCitiesUseCase : FlowUseCase<Unit, ResponseAllCitiesDomain>,
    private val searchInCitiesByPrefix : FlowUseCase<String, List<ResponseCityDomain>>,
    private val mapperAllCitiesDomainToView: Mapper<ResponseAllCitiesDomain, ResponseAllCitiesView>,
    private val mapperCitiesDomainToView: Mapper<ResponseCityDomain, ResponseCityView>,
    private val mapperCitiesViewToNavModel: Mapper<ResponseCityView, NavModelCity>
) : BaseViewModel(){


    // contains data about all cities that are fetch
    private val _allCities = MutableStateFlow<Resource<ResponseAllCitiesView>>(Resource.loading())
    val allCities: StateFlow<Resource<ResponseAllCitiesView>>
        get() = _allCities

    // contains filtered cities based on prefix
    private val _filteredCities = MutableStateFlow<Resource<List<ResponseCityView>>>(Resource.success(listOf()))
    val filteredCities: StateFlow<Resource<List<ResponseCityView>>>
        get() = _filteredCities

    // connected to searchbar via two-way data binding
    val enteredText = MutableStateFlow<String?>(null)

    // combines allCities and enteredText and based on state, computes
    // whether empty state view should be shown or not
    // we use stateIn because combine returns Flow and we want stateFlow instead(to use with data binding)
    val isEmptyStateButtonVisible = allCities.combine(enteredText){ allCities , enteredText ->
        allCities.isSuccess() && enteredText.isNullOrEmpty()
        }.stateIn(viewModelScope, SharingStarted.Lazily, false)


    // combines allCities and filteredCities and based on state, computes
    // a text to be shown in desc(below searchbar)
    // we use stateIn because combine returns Flow and we want stateFlow instead(to use with data binding)
    val descText = allCities.combine(filteredCities){ allCities , filteredCities ->
        takeIf {
            allCities.isSuccess() && enteredText.value.isNullOrEmpty()
        }?.run {
            allCities.data?.size?.let {
                "explore $it cities..."
            }
        }?: kotlin.run {
            takeIf { filteredCities.data.isNullOrEmpty().not() }?.run {
                "${filteredCities.data?.size} result found"
            }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, "")

    /**
     * enteredText is connected to searchbar via two-way data binding
     * listens to enteredText and searches for cities starting with searchbar text
     * this stateflow is started eagerly so it don't need too be observed
     */
    init {
        enteredText.map {
            it?.let { searchByPrefix(it) }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, false)
        getCities()
    }

    /**
     * gets all items at the beginning and fetches
     * data about all cities like size of list
     */
    private fun getCities() {
        viewModelScope.launch {
            _allCities.emit(Resource.loading())
            getAllCitiesUseCase.invoke(Unit).map {
                it.map(mapperAllCitiesDomainToView)
            }.collect {
                _allCities.emit(it)
            }
        }
    }

    /**
     * searches for cities staring with @param prefix
     * since there is no debounce here, we should cancel
     * last job whenever new char entered in edittext
     */
    var searchJob : Job? = null
    private fun searchByPrefix(prefix: String){

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _filteredCities.emit(Resource.loading())
            searchInCitiesByPrefix.invoke(prefix).map {
                it.mapListed(mapperCitiesDomainToView)
            }.collect {
                _filteredCities.emit(it)
            }
        }
    }

    /**
     * maps selected city to NavModel and then navigates user to map fragment
     */
    fun navigateToMap(selectedCity : ResponseCityView){

        navigateTo(NavigationFlow.ToMap(mapperCitiesViewToNavModel.map(selectedCity)))
    }
}