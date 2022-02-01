package com.shamlou.data_local.di

import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.qualifiers.LocalQualifiers.CITIES_FILE_DATA_SOURCE
import com.shamlou.bases.qualifiers.LocalQualifiers.MAPPER_CITIES_LOCAL_TO_DATA
import com.shamlou.bases.readWrite.Readable
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.data_local.dataSources.CitiesFileDataSource
import com.shamlou.data_local.fileReader.ReadFileFromAssets
import com.shamlou.data_local.mappers.cities.MapperCitiesLocalToData
import com.shamlou.data_local.model.cities.ResponseCitiesLocal
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {


    single<Mapper<ResponseCitiesLocal, ResponseCityData>>(MAPPER_CITIES_LOCAL_TO_DATA){ MapperCitiesLocalToData()  }
    single { ReadFileFromAssets(androidContext()) }
    single<Readable<Unit, List<ResponseCityData>>>(CITIES_FILE_DATA_SOURCE){ CitiesFileDataSource(get(MAPPER_CITIES_LOCAL_TO_DATA), get())  }
}