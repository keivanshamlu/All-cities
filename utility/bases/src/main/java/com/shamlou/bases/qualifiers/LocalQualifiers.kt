package com.shamlou.bases.qualifiers

import org.koin.core.qualifier.named

object LocalQualifiers {

    val CITIES_FILE_DATA_SOURCE = named("CITIES_FILE_DATA_SOURCE")
    val CITIES_MEMORY_SOURCE = named("CITIES_MEMORY_SOURCE")
    val MAPPER_CITIES_LOCAL_TO_DATA = named("MAPPER_CITIES_LOCAL_TO_DATA")
}