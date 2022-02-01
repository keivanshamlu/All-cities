package com.shamlou.featureone.di

import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.qualifiers.DomainQualifiers
import com.shamlou.bases.qualifiers.features.CitiesQualifiers.MAPPER_ALL_CITIES_DOMAIN_TO_VIEW
import com.shamlou.bases.qualifiers.features.CitiesQualifiers.MAPPER_CITIES_DOMAIN_TO_VIEW
import com.shamlou.bases.qualifiers.features.CitiesQualifiers.MAPPER_CITIES_VIEW_TO_NAV_MODEL
import com.shamlou.domain.model.cities.ResponseAllCitiesDomain
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.featureone.mappers.MapperAllCitiesDomainToView
import com.shamlou.featureone.mappers.MapperCitiesDomainToView
import com.shamlou.featureone.mappers.MapperCityViewToNavModel
import com.shamlou.featureone.model.posts.ResponseAllCitiesView
import com.shamlou.featureone.model.posts.ResponseCityView
import com.shamlou.featureone.ui.cities.AllCitiesViewModel
import com.shamlou.navigation.model.NavModelCity
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val citiesFeatureModule = module {

    single<Mapper<ResponseCityDomain, ResponseCityView>>(MAPPER_CITIES_DOMAIN_TO_VIEW) { MapperCitiesDomainToView() }
    single<Mapper<ResponseCityView, NavModelCity>>(MAPPER_CITIES_VIEW_TO_NAV_MODEL) { MapperCityViewToNavModel() }
    single<Mapper<ResponseAllCitiesDomain, ResponseAllCitiesView>>(MAPPER_ALL_CITIES_DOMAIN_TO_VIEW) { MapperAllCitiesDomainToView() }
    viewModel {
        AllCitiesViewModel(
            get(DomainQualifiers.GET_CITIES_USE_CASE),
            get(DomainQualifiers.SEARCH_IN_CITIES_BY_PREFIX),
            get(MAPPER_ALL_CITIES_DOMAIN_TO_VIEW),
            get(MAPPER_CITIES_DOMAIN_TO_VIEW),
            get(MAPPER_CITIES_VIEW_TO_NAV_MODEL)
        )
    }
}