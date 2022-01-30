package com.shamlou.domain.di

import com.shamlou.bases.qualifiers.DomainQualifiers.GET_CITIES_USE_CASE
import com.shamlou.bases.qualifiers.RepositoryQualifiers.CITIES_REPO
import com.shamlou.bases.useCase.FlowUseCase
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.domain.useCases.GetAllCitiesUseCase
import org.koin.dsl.module


val domainModule = module {


    single<FlowUseCase<Unit, List<ResponseCityDomain>>>(GET_CITIES_USE_CASE) { GetAllCitiesUseCase(get(CITIES_REPO)) }
}