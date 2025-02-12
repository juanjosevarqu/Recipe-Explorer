package com.varqulabs.recipexplorer.presentation.home

sealed interface HomeRecipesEvent {

    data class Loading(val isLoading: Boolean) : HomeRecipesEvent

    data object Init : HomeRecipesEvent

    data class OnClickRecipe(val recipeId: String) : HomeRecipesEvent

    data class OnSearchRecipe(val query: String) : HomeRecipesEvent

}