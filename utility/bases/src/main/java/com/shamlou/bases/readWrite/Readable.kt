package com.shamlou.bases.readWrite

interface Readable<I, O> {

    suspend fun read(input: I): O
}
