package com.shamlou.featureone.di

import com.shamlou.bases.qualifiers.DomainQualifiers
import com.shamlou.featureone.ui.cities.AllCitiesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val citiesFeatureModule = module {

    viewModel { AllCitiesViewModel(get(DomainQualifiers.GET_CITIES_USE_CASE)) }
}