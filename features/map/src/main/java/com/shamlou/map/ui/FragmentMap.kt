package com.shamlou.map.ui

import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.shamlou.bases_android.fragment.BaseFragment
import com.shamlou.map.R
import com.shamlou.map.databinding.FragmentMapBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentMap: BaseFragment<MapsViewModel, FragmentMapBinding>() , OnMapReadyCallback {

    override val viewModel: MapsViewModel by viewModel()
    override val layoutRes: Int = R.layout.fragment_map
    private lateinit var mMap: GoogleMap
    override fun hookVariables() {
        binding?.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        val moveCamera = mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}