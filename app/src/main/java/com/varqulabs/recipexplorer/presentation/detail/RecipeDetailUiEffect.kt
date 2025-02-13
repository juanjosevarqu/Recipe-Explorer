package com.varqulabs.recipexplorer.presentation.detail

sealed class RecipeDetailUiEffect {

    data object GoBack : RecipeDetailUiEffect()

    data class NavigateToRecipeLocation(val areaName: String) : RecipeDetailUiEffect()

}