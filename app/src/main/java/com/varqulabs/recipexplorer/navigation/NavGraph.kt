package com.varqulabs.recipexplorer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
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
                navController.navigate(Routes.RecipeDetail(recipeId))
            }
        )

        composable<Routes.RecipeDetail> { backStackEntry ->
            val recipeId = backStackEntry.toRoute<Routes.RecipeDetail>().recipeId

        }
    }
}