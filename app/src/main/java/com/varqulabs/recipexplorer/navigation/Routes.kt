package com.varqulabs.recipexplorer.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object Home : Routes()

    @Serializable
    data class RecipeDetail(val recipeId: String) : Routes()

    @Serializable
    data class Maps(val latitude: Double, val longitude: Double, val areaName: String? = null) : Routes()

}