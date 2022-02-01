package com.shamlou.domain.useCases

import com.shamlou.bases.useCase.FlowUseCase
import com.shamlou.bases.useCase.Resource
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.domain.repository.CitiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * searches for all cities started with specific prefix
 */
class SearchInCitiesByPrefix(
    private val repository: CitiesRepository
) : FlowUseCase<String, List<ResponseCityDomain>> {

    override fun execute(parameters: String): Flow<Resource<List<ResponseCityDomain>>> {
        return repository.searchInCitiesByPrefix().map {
            Resource.success(it.search(parameters))
        }
    }
}