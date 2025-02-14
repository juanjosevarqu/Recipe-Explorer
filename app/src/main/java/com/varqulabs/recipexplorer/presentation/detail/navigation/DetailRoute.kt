package com.varqulabs.recipexplorer.presentation.detail.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.varqulabs.recipexplorer.navigation.Routes
import com.varqulabs.recipexplorer.presentation.detail.RecipeDetailEvent.Init
import com.varqulabs.recipexplorer.presentation.detail.RecipeDetailScreen
import com.varqulabs.recipexplorer.presentation.detail.RecipeDetailUiEffect
import com.varqulabs.recipexplorer.presentation.detail.RecipeDetailViewModel

fun NavGraphBuilder.detailRoute(
    onBack: () -> Unit,
    onOpenRecipeLocation: (areaName: String) -> Unit
) {
    composable<Routes.RecipeDetail> {

        val viewModel = hiltViewModel<RecipeDetailViewModel>()

        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val eventHandler = viewModel::eventHandler

        LaunchedEffect(state.reload) { if (state.reload) eventHandler(Init) }

        RecipeDetailScreen(
            state = state,
            eventHandler = eventHandler,
        )

        LaunchedEffect(Unit) {
            viewModel.uiEffect.collect {
                when(it) {
                    is RecipeDetailUiEffect.GoBack -> onBack()
                    is RecipeDetailUiEffect.NavigateToRecipeLocation -> onOpenRecipeLocation(it.areaName)
                }
            }
        }
    }
}