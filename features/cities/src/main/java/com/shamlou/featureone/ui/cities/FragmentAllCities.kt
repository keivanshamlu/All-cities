package com.shamlou.featureone.ui.cities


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.shamlou.bases.useCase.Resource
import com.shamlou.bases_android.fragment.BaseFragment
import com.shamlou.bases_android.recyclerview.adapter.onItemsChanged
import com.shamlou.bases_android.recyclerview.adapter.scrollToTop
import com.shamlou.featureone.R
import com.shamlou.featureone.databinding.FragmentAllCitiesBinding
import com.shamlou.featureone.ui.cities.citiesList.CitiesAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentAllCities : BaseFragment<AllCitiesViewModel, FragmentAllCitiesBinding>() {

    override val viewModel: AllCitiesViewModel by viewModel()
    override val layoutRes: Int = R.layout.fragment_all_cities

    override fun hookVariables() {
        binding?.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerview()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                //since android studio does not support flow in data binding i have to do this
                viewModel.isEmptyStateButtonVisible.collect {

                    binding?.imageViewEmptyState?.isVisible = it
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                //since android studio does not support flow in data binding i have to do this
                viewModel.descText.collect {

                    binding?.textViewDesc?.text = it
                }
            }
        }

        //todo change it to data binding stuff
        binding?.editText?.addTextChangedListener {
            viewModel.searchByPrefix(it.toString())
        }
    }

    private fun setUpRecyclerview() {

        binding?.recyclerviewCities?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CitiesAdapter().apply {

                // make list show first item everytime list
                // updated(since it's sorted user needs to see first items)
                onItemsChanged {
                    scrollToTop()
                }
            }
        }
    }
}