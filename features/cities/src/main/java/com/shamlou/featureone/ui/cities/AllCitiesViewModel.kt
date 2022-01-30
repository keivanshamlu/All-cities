package com.shamlou.featureone.ui.cities

import androidx.lifecycle.viewModelScope
import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.useCase.FlowUseCase
import com.shamlou.bases.useCase.Resource
import com.shamlou.bases.useCase.map
import com.shamlou.bases.useCase.mapListed
import com.shamlou.bases_android.viewModel.BaseViewModel
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.featureone.model.posts.ResponseCityView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AllCitiesViewModel(
    private val getAllCitiesUseCase : FlowUseCase<Unit, List<ResponseCityDomain>>,
    private val mapperCitiesDomainToView: Mapper<ResponseCityDomain, ResponseCityView>,
) : BaseViewModel(){


    private val _cities = MutableStateFlow<Resource<List<ResponseCityView>>>(Resource.loading())
    val cities: StateFlow<Resource<List<ResponseCityView>>>
        get() = _cities

    init {

        getCities()
    }
    private fun getCities() {
        viewModelScope.launch {
            _cities.emit(Resource.loading())
            getAllCitiesUseCase.invoke(Unit).map {
                it.mapListed(mapperCitiesDomainToView)
            }.collect {
                _cities.emit(it)
            }
        }
    }
}