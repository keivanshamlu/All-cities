package com.shamlou.domain.di

import com.shamlou.bases.qualifiers.DomainQualifiers.GET_CITIES_USE_CASE
import com.shamlou.bases.qualifiers.DomainQualifiers.SEARCH_IN_CITIES_BY_PREFIX
import com.shamlou.bases.qualifiers.RepositoryQualifiers.CITIES_REPO
import com.shamlou.bases.useCase.FlowUseCase
import com.shamlou.domain.model.cities.ResponseAllCitiesDomain
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.domain.useCases.GetAllCitiesUseCase
import com.shamlou.domain.useCases.SearchInCitiesByPrefix
import org.koin.dsl.module

val domainModule = module {

    single<FlowUseCase<Unit, ResponseAllCitiesDomain>>(GET_CITIES_USE_CASE) { GetAllCitiesUseCase(get(CITIES_REPO)) }
    single<FlowUseCase<String, List<ResponseCityDomain>>>(SEARCH_IN_CITIES_BY_PREFIX) { SearchInCitiesByPrefix(get(CITIES_REPO)) }
}