package com.varqulabs.recipexplorer.presentation.home.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.varqulabs.recipexplorer.navigation.Routes
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesScreen
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesUiEffect
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesViewModel

fun NavGraphBuilder.homeRoute(
    openRecipeDetail: (String) -> Unit
) {
    composable<Routes.Home> {

        val viewModel = hiltViewModel<HomeRecipesViewModel>()

        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val eventHandler = viewModel::eventHandler

        HomeRecipesScreen(
            state = state,
            eventHandler = eventHandler,
        )

        LaunchedEffect(Unit) {
            viewModel.uiEffect.collect {
                when(it) {
                    is HomeRecipesUiEffect.NavigateToRecipeDetail -> openRecipeDetail(it.recipeId)
                    is HomeRecipesUiEffect.ShowError -> {}
                }
            }
        }
    }
}