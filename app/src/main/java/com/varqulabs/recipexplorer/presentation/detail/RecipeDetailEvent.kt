package com.varqulabs.recipexplorer.presentation.detail

sealed interface RecipeDetailEvent {

    data class Loading(val isLoading: Boolean) : RecipeDetailEvent

    data object Init : RecipeDetailEvent

    data object OnBack : RecipeDetailEvent

    data class OnOpenMapLocation(val areaName: String) : RecipeDetailEvent

}