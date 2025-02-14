package com.varqulabs.recipexplorer.fakes

import com.varqulabs.recipexplorer.core.domain.DataState
import com.varqulabs.recipexplorer.domain.model.Recipe
import com.varqulabs.recipexplorer.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMealsRepository : MealsRepository {
    var recipesBySearch: List<Recipe> = emptyList()
    var recipesByFirstLetter: List<Recipe> = emptyList()

    var shouldReturnError: Boolean = false

    override suspend fun getRecipeById(id: String): Flow<DataState<Recipe>> = flow {}

    override suspend fun searchRecipesByName(name: String): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.Loading)
        if (shouldReturnError) {
            emit(DataState.Error("Error de búsqueda por nombre"))
        } else {
            emit(DataState.Success(recipesBySearch))
        }
    }

    override suspend fun getRecipesByFirstLetter(letter: String): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.Loading)
        if (shouldReturnError) {
            emit(DataState.Error("Error de conexión"))
        } else {
            emit(DataState.Success(recipesByFirstLetter))
        }
    }
}