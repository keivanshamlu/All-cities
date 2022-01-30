package com.shamlou.sample.di

import com.shamlou.data.di.repositoryModule
import com.shamlou.data_local.di.localModule
import com.shamlou.domain.di.domainModule
import com.shamlou.featureone.di.citiesFeatureModule

val koinModules = listOf(
    appModule,
    repositoryModule,
    localModule,
    domainModule,
    citiesFeatureModule
)