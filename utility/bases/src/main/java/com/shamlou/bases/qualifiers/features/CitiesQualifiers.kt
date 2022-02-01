package com.shamlou.bases.qualifiers.features

import org.koin.core.qualifier.named

object CitiesQualifiers {

    val MAPPER_CITIES_DOMAIN_TO_VIEW = named("MAPPER_CITIES_DOMAIN_TO_VIEW")
    val MAPPER_ALL_CITIES_DOMAIN_TO_VIEW = named("MAPPER_ALL_CITIES_DOMAIN_TO_VIEW")
}