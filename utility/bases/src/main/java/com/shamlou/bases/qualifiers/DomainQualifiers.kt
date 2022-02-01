package com.shamlou.bases.qualifiers

import org.koin.core.qualifier.named

object DomainQualifiers {

    val GET_CITIES_USE_CASE = named("GET_CITIES_USE_CASE")
    val SEARCH_IN_CITIES_BY_PREFIX = named("SEARCH_IN_CITIES_BY_PREFIX")
}