package com.varqulabs.recipexplorer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.varqulabs.recipexplorer.navigation.utils.navigateBack
import com.varqulabs.recipexplorer.navigation.utils.navigateTo
import com.varqulabs.recipexplorer.presentation.detail.navigation.detailRoute
import com.varqulabs.recipexplorer.presentation.home.navigation.homeRoute

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

        composable<Routes.MapLocation> { backStackEntry ->
            val areaName = backStackEntry.toRoute<Routes.MapLocation>().areaName
        }
    }
}