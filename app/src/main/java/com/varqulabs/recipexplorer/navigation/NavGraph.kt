package com.varqulabs.recipexplorer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.varqulabs.recipexplorer.navigation.utils.navigateBack
import com.varqulabs.recipexplorer.navigation.utils.navigateTo
import com.varqulabs.recipexplorer.presentation.detail.navigation.detailRoute
import com.varqulabs.recipexplorer.presentation.home.navigation.homeRoute
import com.varqulabs.recipexplorer.presentation.map.navigation.mapRoute

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home,
        modifier = modifier
    ) {

        homeRoute(
            openRecipeDetail = { recipeId ->
                navController.navigateTo(Routes.RecipeDetail(recipeId))
            }
        )

        detailRoute(
            onBack = { navController.navigateBack() },
            onOpenRecipeLocation = { areaName ->
                navController.navigateTo(Routes.MapLocation(areaName))
            }
        )

        mapRoute(
            onBack = { navController.navigateBack() }
        )
    }
}