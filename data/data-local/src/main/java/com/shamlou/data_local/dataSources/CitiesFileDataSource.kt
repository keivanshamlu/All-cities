package com.shamlou.data_local.dataSources

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.readWrite.Readable
import com.shamlou.data.model.cities.ResponseCityData
import com.shamlou.data_local.BuildConfig
import com.shamlou.data_local.fileReader.ReadFileFromAssets
import com.shamlou.data_local.model.cities.ResponseCityLocal

/**
 * this class is a dataSource that fetches data from file
 */
class CitiesFileDataSource(
    private val mapperCityLocalToData: Mapper<ResponseCityLocal, ResponseCityData>,
    private val fileReader : ReadFileFromAssets,
    private val gson: Gson
) : Readable<Unit, List<ResponseCityData>> {

    /**
     * calls [ReadFileFromAssets] and gets read string and then
     * calls [getList] to convert it to kotlin objects and then using
     * [mapperCityLocalToData] it convert that list to data model and return it
     * file name will be provided via BuildConfig
     */
    override suspend fun read(input: Unit): List<ResponseCityData> {

        fileReader.readFile(BuildConfig.CITIES_FILE_NAME).also {
            return getList(it)?.map { mapperCityLocalToData.map(it) }?: listOf()
        }
    }

    /**
     * gets a json string and returns list of kotlin objects
     */
    private fun getList(jsonArray: String?): List<ResponseCityLocal>? {
        TypeToken.getParameterized(MutableList::class.java, ResponseCityLocal::class.java).type.also {
            return gson.fromJson(jsonArray, it)
        }
    }
}