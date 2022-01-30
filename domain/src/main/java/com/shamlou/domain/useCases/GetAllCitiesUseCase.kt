package com.shamlou.domain.useCases

import com.shamlou.bases.useCase.FlowUseCase
import com.shamlou.bases.useCase.Resource
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.domain.repository.CitiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllCitiesUseCase(
    private val repository: CitiesRepository
) : FlowUseCase<Unit, List<ResponseCityDomain>> {

    override fun execute(parameters: Unit): Flow<Resource<List<ResponseCityDomain>>> {
        return repository.getAllCities().map {
            Resource.success(it)
        }
    }
}