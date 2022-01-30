package com.shamlou.featureone.ui.cities


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.shamlou.bases.useCase.Resource
import com.shamlou.bases_android.fragment.BaseFragment
import com.shamlou.featureone.R
import com.shamlou.featureone.databinding.FragmentAllCitiesBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentAllCities : BaseFragment<AllCitiesViewModel , FragmentAllCitiesBinding>(){

    override val viewModel: AllCitiesViewModel by viewModel()
    override val layoutRes: Int = R.layout.fragment_all_cities

    override fun hookVariables() {
        binding?.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cities.collect {

                    if(it.status == Resource.Status.LOADING) viewModel.showToast("laoding")
                    if(it.status == Resource.Status.SUCCESS){
                        viewModel.showToast(it.data?.size.toString())
                    }
                    if(it.status == Resource.Status.ERROR) viewModel.showToast(it.error?.localizedMessage?:"")
                }
            }
        }
    }
}