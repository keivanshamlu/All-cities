package com.shamlou.bases.useCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

interface FlowUseCase<P, R> {
    fun invoke(parameters: P): Flow<Resource<R>> {
        try {
            return execute(parameters)
                .onStart { emit(Resource.loading()) }
                .catch { e -> emit(Resource.error(e)) }
                .flowOn(Dispatchers.IO)
        } catch (e: Exception) {
            return flow { emit(Resource.error(e)) }
        }
    }

    fun execute(parameters: P): Flow<Resource<R>>
}
