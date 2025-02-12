package com.varqulabs.recipexplorer.presentation.home

import androidx.compose.runtime.Stable
import com.varqulabs.recipexplorer.domain.model.Recipe

@Stable
data class HomeRecipesState(
    val reload: Boolean = true,
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
    val recipes: List<Recipe> = emptyList(),
    val recipesFiltered: List<Recipe> = emptyList(),
)