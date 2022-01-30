package com.shamlou.bases.readWrite

interface Writable<I, O> {

    suspend fun write(input: I): O
}