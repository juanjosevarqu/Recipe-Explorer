package com.varqulabs.recipexplorer.data.remote

import com.varqulabs.recipexplorer.data.remote.dto.RecipesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsService {

    @GET("lookup.php")
    suspend fun getRecipeById(@Query("i") id: String): Response<RecipesDto>

    @GET("search.php")
    suspend fun searchRecipesByName(@Query("s") name: String): Response<RecipesDto>

    @GET("search.php")
    suspend fun searchRecipesByFirstLetter(@Query("f") name: String): Response<RecipesDto>

}
