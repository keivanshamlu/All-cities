package com.shamlou.data_local.di

import com.shamlou.bases.readWrite.Readable
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.data_local.dataSources.CitiesFileDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {


    single<Readable<Unit, ResponseCityData>>(named("")){ CitiesFileDataSource(get())  }
}