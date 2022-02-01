package com.shamlou.map.di

import com.shamlou.map.ui.MapsViewModel
import com.shamlou.navigation.model.NavModelCity
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val mapFeatureModule = module {

    viewModel { (navModelCity : NavModelCity) -> MapsViewModel(navModelCity) }
}