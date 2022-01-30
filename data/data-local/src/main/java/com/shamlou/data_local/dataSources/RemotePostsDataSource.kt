package com.shamlou.data_local.dataSources

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.readWrite.Readable
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.data_local.BuildConfig
import com.shamlou.data_local.fileReader.ReadFileFromAssets
import com.shamlou.data_local.model.cities.ResponseCitiesLocal

class CitiesFileDataSource(
    private val mapperCitiesDataToLocal: Mapper<ResponseCitiesLocal, ResponseCityData>,
    private val fileReader : ReadFileFromAssets
) : Readable<Unit, List<ResponseCityData>> {
    override suspend fun read(input: Unit): List<ResponseCityData> {

        val lastTime = System.currentTimeMillis()
        fileReader.readFile(BuildConfig.CITIES_FILE_NAME).also {
            Log.d("read time" , (System.currentTimeMillis() - lastTime).toString())
            return getList(it)?.map { mapperCitiesDataToLocal.map(it) }?: listOf()
        }
    }

    private fun getList(jsonArray: String?): List<ResponseCitiesLocal>? {
        TypeToken.getParameterized(MutableList::class.java, ResponseCitiesLocal::class.java).type.also {
            return Gson().fromJson(jsonArray, it)
        }
    }
}