package com.shamlou.featureone.di

import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.qualifiers.DomainQualifiers
import com.shamlou.bases.qualifiers.features.CitiesQualifiers.MAPPER_CITIES_DOMAIN_TO_VIEW
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.featureone.mappers.MapperCitiesDomainToView
import com.shamlou.featureone.model.posts.ResponseCityView
import com.shamlou.featureone.ui.cities.AllCitiesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val citiesFeatureModule = module {

    single<Mapper<ResponseCityDomain, ResponseCityView>>(MAPPER_CITIES_DOMAIN_TO_VIEW) { MapperCitiesDomainToView() }
    viewModel {
        AllCitiesViewModel(
            get(DomainQualifiers.GET_CITIES_USE_CASE),
            get(MAPPER_CITIES_DOMAIN_TO_VIEW)
        )
    }
}