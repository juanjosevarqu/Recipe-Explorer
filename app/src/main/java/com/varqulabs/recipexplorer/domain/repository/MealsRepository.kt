package com.varqulabs.recipexplorer.domain.repository

import com.varqulabs.recipexplorer.core.domain.DataState
import com.varqulabs.recipexplorer.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface MealsRepository {

    suspend fun getRecipeById(id: String): Flow<DataState<Recipe>>

    suspend fun searchRecipesByName(name: String): Flow<DataState<List<Recipe>>>

    suspend fun getRecipesByFirstLetter(letter: String): Flow<DataState<List<Recipe>>>

}