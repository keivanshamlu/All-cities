package com.shamlou.data.di

import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.qualifiers.LocalQualifiers
import com.shamlou.bases.qualifiers.RepositoryQualifiers.CITIES_REPO
import com.shamlou.bases.qualifiers.RepositoryQualifiers.MAPPER_CITIES_DATA_TO_DOMAIN
import com.shamlou.data.mappers.MapperCitiesDataToDomain
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.data.repoImpl.CitiesRepositoryImpl
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.domain.repository.CitiesRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<Mapper<ResponseCityData, ResponseCityDomain>>(MAPPER_CITIES_DATA_TO_DOMAIN) { MapperCitiesDataToDomain() }
    single<CitiesRepository>(CITIES_REPO) {
        CitiesRepositoryImpl(
            get(LocalQualifiers.CITIES_FILE_DATA_SOURCE),
            get(MAPPER_CITIES_DATA_TO_DOMAIN)
        )
    }
}