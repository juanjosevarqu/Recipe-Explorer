package com.varqulabs.recipexplorer.data.remote

import com.varqulabs.recipexplorer.data.remote.dto.RecipesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDbService {

    @GET("lookup.php")
    suspend fun getRecipeById(@Query("i") id: String): Response<RecipesDto>

    @GET("search.php")
    suspend fun searchRecipesByName(@Query("s") name: String): Response<RecipesDto>

    @GET("filter.php")
    suspend fun getRecipesByIngredient(@Query("i") ingredient: String): Response<RecipesDto>
}
