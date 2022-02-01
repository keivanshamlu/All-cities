package com.shamlou.map.ui

import com.google.android.gms.maps.model.LatLng
import com.shamlou.bases_android.viewModel.BaseViewModel
import com.shamlou.navigation.model.NavModelCity
import kotlinx.coroutines.flow.MutableSharedFlow

class MapsViewModel(private val navModelCity : NavModelCity) : BaseViewModel() {

    private val _showLocationEvent = MutableSharedFlow<LatLng>(replay = 1)
    val showLocationEvent: MutableSharedFlow<LatLng>
        get() = _showLocationEvent

    /**
     * whenever map is ready to show a location, it will map selected loc to
     * LatLng and emit it on [_showLocationEvent] which is a event and this
     * event will be observed in Fragment to be shown in map
     */
    fun onMapReady(){

        navModelCity.coord.apply {
            _showLocationEvent.tryEmit(LatLng(lat , lon))
        }
    }
}