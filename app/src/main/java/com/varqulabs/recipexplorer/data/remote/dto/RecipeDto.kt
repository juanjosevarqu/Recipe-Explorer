package com.varqulabs.recipexplorer.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.varqulabs.recipexplorer.domain.model.Recipe

data class RecipesDto(
    @SerializedName("meals") val recipes: List<RecipeDto>? = emptyList(),
)

data class RecipeDto(
    @SerializedName("idMeal") val id: String? = "",
    @SerializedName("strMeal") val name: String? = "",
    @SerializedName("strMealThumb") val imageUrl: String? = "",
    @SerializedName("strArea") val area: String? = "",
    @SerializedName("strInstructions") val instructions: String? = "",
)

fun RecipeDto.mapToModel() = Recipe(
    id = id.orEmpty(),
    name = name.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    area = area.orEmpty(),
    instructions = instructions.orEmpty(),
)