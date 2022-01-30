package com.shamlou.bases.mapper

interface Mapper<First, Second> {

    fun map(first: First): Second
}