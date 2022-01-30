package com.shamlou.featureone.ui.cities

import com.shamlou.bases.useCase.FlowUseCase
import com.shamlou.bases_android.viewModel.BaseViewModel
import com.shamlou.domain.model.cities.ResponseCityDomain

class AllCitiesViewModel(
    private val getAllCitiesUseCase : FlowUseCase<Unit, List<ResponseCityDomain>>
) : BaseViewModel(){


}