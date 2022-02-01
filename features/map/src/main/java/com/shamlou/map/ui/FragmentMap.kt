package com.shamlou.map.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.shamlou.bases_android.fragment.BaseFragment
import com.shamlou.map.R
import com.shamlou.map.databinding.FragmentMapBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import com.google.android.gms.maps.model.CameraPosition




class FragmentMap: BaseFragment<MapsViewModel, FragmentMapBinding>() , OnMapReadyCallback {

    private val args : FragmentMapArgs by navArgs()
    override val viewModel: MapsViewModel by viewModel{
        parametersOf(args.selectedCity)
    }
    override val layoutRes: Int = R.layout.fragment_map
    private lateinit var mMap: GoogleMap
    override fun hookVariables() {
        binding?.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpMap()
        observeViewModel()

    }

    private fun observeViewModel(){


        //i use repeatOnLifecycle to prevent bugs that may accrue when app is in background
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.showLocationEvent.collect {

                    moveAndShowLocation(it)
                }
            }
        }
    }

    /**
     * sets up map
     */
    private fun setUpMap(){

        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).apply {
            getMapAsync(this@FragmentMap)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        viewModel.onMapReady()
    }

    /**
     * adds marker and moves camera to given location
     */
    private fun moveAndShowLocation(loc : LatLng){
        if(::mMap.isInitialized.not())return
        mMap.apply {

            addMarker(MarkerOptions().position(loc).title(getString(R.string.selected_city)))
            moveCamera(CameraUpdateFactory.newLatLng(loc))
            val cameraPosition = CameraPosition.Builder()
                .target(loc)
                .zoom(10f).build()
            //Zoom in and animate the camera.
            animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }
}