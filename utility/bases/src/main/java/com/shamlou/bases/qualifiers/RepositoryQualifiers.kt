package com.shamlou.bases.qualifiers

import org.koin.core.qualifier.named

object RepositoryQualifiers {

    val MAPPER_CITIES_DATA_TO_DOMAIN = named("MAPPER_CITIES_DATA_TO_DOMAIN")
    val MAPPER_ALL_CITIES_DATA_TO_DOMAIN = named("MAPPER_ALL_CITIES_DATA_TO_DOMAIN")
    val CITIES_REPO = named("CITIES_REPO")
}