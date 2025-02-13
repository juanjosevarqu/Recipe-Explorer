package com.varqulabs.recipexplorer.presentation.map.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.varqulabs.recipexplorer.domain.constants.areaCoordinates
import com.varqulabs.recipexplorer.navigation.Routes
import com.varqulabs.recipexplorer.presentation.map.MapLocationScreen

fun NavGraphBuilder.mapRoute(
    onBack: () -> Unit
) {
    composable<Routes.MapLocation> { backStackEntry ->

        val areaName = backStackEntry.toRoute<Routes.MapLocation>().areaName

        val latitude = areaCoordinates[areaName]?.first ?: 0.0
        val longitude = areaCoordinates[areaName]?.second ?: 0.0

        MapLocationScreen(
            latitude = latitude,
            longitude = longitude,
            onBack = onBack
        )
    }
}