package com.shamlou.map.di

import com.shamlou.map.ui.MapsViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val mapFeatureModule = module {

    viewModel { MapsViewModel() }
}