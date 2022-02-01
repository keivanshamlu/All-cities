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
import com.shamlou.featureone.model.posts.ResponseCityView
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AllCitiesViewModel(
    private val getAllCitiesUseCase : FlowUseCase<Unit, ResponseAllCitiesDomain>,
    private val searchInCitiesByPrefix : FlowUseCase<String, List<ResponseCityDomain>>,
    private val mapperAllCitiesDomainToView: Mapper<ResponseAllCitiesDomain, ResponseCityView>,
    private val mapperCitiesDomainToView: Mapper<ResponseCityDomain, ResponseCityView>,
) : BaseViewModel(){


    private val _allCities = MutableStateFlow<Resource<ResponseCityView>>(Resource.loading())
    val allCities: StateFlow<Resource<ResponseCityView>>
        get() = _allCities

    private val _filteredCities = MutableStateFlow<Resource<List<ResponseCityView>>>(Resource.loading())
    val filteredCities: StateFlow<Resource<List<ResponseCityView>>>
        get() = _filteredCities

    init {

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
    fun searchByPrefix(prefix: String){

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
}