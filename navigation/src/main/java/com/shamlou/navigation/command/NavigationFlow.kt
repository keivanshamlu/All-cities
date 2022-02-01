package com.shamlou.navigation.command

import androidx.navigation.NavDirections
import com.shamlou.featureone.ui.cities.FragmentAllCitiesDirections
import com.shamlou.navigation.command.NavigationFlow.*
import com.shamlou.navigation.model.NavModelCity

sealed class NavigationFlow {
    data class ToMap(val navModelCity: NavModelCity) : NavigationFlow()
}


fun NavigationFlow.toNavDirections(): NavDirections =
    when (this) {
        is ToMap -> FragmentAllCitiesDirections.actionFragmentAllCitiesToMap(navModelCity)
    }