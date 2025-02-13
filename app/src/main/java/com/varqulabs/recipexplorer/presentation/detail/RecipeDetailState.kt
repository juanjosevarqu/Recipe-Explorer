package com.varqulabs.recipexplorer.presentation.detail

import com.varqulabs.recipexplorer.domain.model.Recipe

data class RecipeDetailState(
    val reload: Boolean = true,
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
    val recipeId: String = "",
    val recipeDetail: Recipe = Recipe(),
)