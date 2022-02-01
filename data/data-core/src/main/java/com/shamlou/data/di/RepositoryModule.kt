package com.shamlou.data.di

import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.qualifiers.LocalQualifiers
import com.shamlou.bases.qualifiers.RepositoryQualifiers.CITIES_REPO
import com.shamlou.bases.qualifiers.RepositoryQualifiers.MAPPER_ALL_CITIES_DATA_TO_DOMAIN
import com.shamlou.bases.qualifiers.RepositoryQualifiers.MAPPER_CITIES_DATA_TO_DOMAIN
import com.shamlou.data.dataSource.CitiesMemoryDataSource
import com.shamlou.data.mappers.MapperAllCitiesDataToDomain
import com.shamlou.data.mappers.MapperCitiesDataToDomain
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.data.repoImpl.CitiesRepositoryImpl
import com.shamlou.domain.model.cities.ResponseAllCitiesDomain
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.domain.repository.CitiesRepository
import org.koin.dsl.binds
import org.koin.dsl.module
import com.shamlou.bases.readWrite.Readable
import com.shamlou.bases.readWrite.Writable

val repositoryModule = module {

    single<Mapper<ResponseCityData, ResponseCityDomain>>(MAPPER_CITIES_DATA_TO_DOMAIN) { MapperCitiesDataToDomain() }
    single<Mapper<List<ResponseCityData>, ResponseAllCitiesDomain>>(MAPPER_ALL_CITIES_DATA_TO_DOMAIN) { MapperAllCitiesDataToDomain() }
    single { CitiesMemoryDataSource(get(MAPPER_CITIES_DATA_TO_DOMAIN)) } binds arrayOf(Readable::class, Writable::class)
    single<CitiesRepository>(CITIES_REPO) {
        CitiesRepositoryImpl(
            get(LocalQualifiers.CITIES_FILE_DATA_SOURCE),
            get(),
            get(),
            get(MAPPER_ALL_CITIES_DATA_TO_DOMAIN),
        )
    }
}