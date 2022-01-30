package com.shamlou.featureone.ui.cities


import com.shamlou.bases_android.fragment.BaseFragment
import com.shamlou.featureone.R
import com.shamlou.featureone.databinding.FragmentAllCitiesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentAllCities : BaseFragment<AllCitiesViewModel , FragmentAllCitiesBinding>(){

    override val viewModel: AllCitiesViewModel by viewModel()
    override val layoutRes: Int = R.layout.fragment_all_cities

    override fun hookVariables() {
        binding?.viewModel = viewModel
    }
}