package com.shamlou.navigation.command

import androidx.navigation.NavDirections
import com.shamlou.featureone.ui.cities.FragmentAllCitiesDirections
import com.shamlou.navigation.command.NavigationFlow.*

sealed class NavigationFlow {
    data class ToMap(val showId: Long) : NavigationFlow()
}


fun NavigationFlow.toNavDirections(): NavDirections =
    when (this) {
        is ToMap -> FragmentAllCitiesDirections.actionFragmentAllCitiesToMap()
    }