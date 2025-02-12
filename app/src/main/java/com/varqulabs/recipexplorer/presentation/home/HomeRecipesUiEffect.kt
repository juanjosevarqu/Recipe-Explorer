package com.varqulabs.recipexplorer.presentation.home

sealed class HomeRecipesUiEffect {

    data class ShowError(val message: String) : HomeRecipesUiEffect()

    data class NavigateToRecipeDetail(val recipeId: String) : HomeRecipesUiEffect()

}