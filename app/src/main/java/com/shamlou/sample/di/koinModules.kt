package com.shamlou.sample.di

import com.shamlou.data.di.repositoryModule
import com.shamlou.data_local.di.localModule
import com.shamlou.domain.di.domainModule
import com.shamlou.featureone.di.citiesFeatureModule
import com.shamlou.map.di.mapFeatureModule

// all koi modules of project should be here
val koinModules = listOf(
    repositoryModule,
    localModule,
    domainModule,
    citiesFeatureModule,
    mapFeatureModule
)