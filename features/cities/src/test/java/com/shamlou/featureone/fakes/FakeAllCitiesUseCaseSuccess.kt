package com.shamlou.featureone.fakes

import com.shamlou.bases.useCase.FlowUseCase
import com.shamlou.bases.useCase.Resource
import com.shamlou.domain.model.cities.ResponseAllCitiesDomain
import com.shamlou.domain.model.cities.ResponseCityDomain
import com.shamlou.featureone.Fakes.sampleError
import com.shamlou.featureone.Fakes.validAllCityDomain
import com.shamlou.featureone.Fakes.validCitiyListResponseDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

const val RESOURCES_PACKAGE = "com.shamlou.bases.useCase.ResourceKt"
//s ince mockk does not support stubbing for base classes,
// i created these 2 fake classes so i can stub invoke()
class FakeAllCitiesUseCaseSuccess : FlowUseCase<Unit, ResponseAllCitiesDomain> {
    private val responseDomain = validAllCityDomain
    override fun execute(parameters: Unit): Flow<Resource<ResponseAllCitiesDomain>> =
        flow {
            emit(Resource.loading())
            emit(Resource.success(responseDomain))
        }

    override fun invoke(parameters: Unit): Flow<Resource<ResponseAllCitiesDomain>> =
        flow {
            emit(Resource.loading())
            emit(Resource.success(responseDomain))
        }

}

class FakeAllCitiesUseCaseFailure : FlowUseCase<Unit, ResponseAllCitiesDomain> {
    override fun execute(parameters: Unit): Flow<Resource<ResponseAllCitiesDomain>> =
        flow {
            emit(Resource.loading())
            emit(Resource.error(sampleError))
        }

    override fun invoke(parameters: Unit): Flow<Resource<ResponseAllCitiesDomain>> =
        flow {
            emit(Resource.loading())
            emit(Resource.error(sampleError))
        }

}


class FakeGetCitiesUseCaseSuccess : FlowUseCase<String, List<ResponseCityDomain>> {
    private val responseDomain = validCitiyListResponseDomain
    override fun execute(parameters: String): Flow<Resource<List<ResponseCityDomain>>> =
        flow {
            emit(Resource.loading())
            emit(Resource.success(responseDomain))
        }

    override fun invoke(parameters: String): Flow<Resource<List<ResponseCityDomain>>> =
        flow {
            emit(Resource.loading())
            emit(Resource.success(responseDomain))
        }
}

class FakeGetCitiesUseCaseFailure : FlowUseCase<String, List<ResponseCityDomain>> {
    override fun execute(parameters: String): Flow<Resource<List<ResponseCityDomain>>> =
        flow {
            emit(Resource.loading())
            emit(Resource.error(sampleError))
        }

    override fun invoke(parameters: String): Flow<Resource<List<ResponseCityDomain>>> =
        flow {
            emit(Resource.loading())
            emit(Resource.error(sampleError))
        }

}