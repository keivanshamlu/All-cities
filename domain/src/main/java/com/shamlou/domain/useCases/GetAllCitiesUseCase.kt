package com.shamlou.domain.useCases

import com.shamlou.bases.useCase.FlowUseCase
import com.shamlou.bases.useCase.Resource
import com.shamlou.domain.model.cities.ResponseAllCitiesDomain
import com.shamlou.domain.repository.CitiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * calls repository to get all cities and then returns
 * info about all cities like size of all cities
 */
class GetAllCitiesUseCase(
    private val repository: CitiesRepository
) : FlowUseCase<Unit, ResponseAllCitiesDomain> {

    override fun execute(parameters: Unit): Flow<Resource<ResponseAllCitiesDomain>> {
        return repository.getAndSaveAllCities().map {
            Resource.success(it)
        }
    }
}