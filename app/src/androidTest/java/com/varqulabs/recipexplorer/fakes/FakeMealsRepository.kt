package com.varqulabs.recipexplorer.fakes

import com.varqulabs.recipexplorer.core.domain.DataState
import com.varqulabs.recipexplorer.domain.model.Recipe
import com.varqulabs.recipexplorer.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMealsRepository : MealsRepository {

    override suspend fun getRecipeById(id: String): Flow<DataState<Recipe>> = flow {
        emit(DataState.Success(Recipe(id = "123", name = "Fake Recipe")))
    }

    override suspend fun searchRecipesByName(name: String): Flow<DataState<List<Recipe>>> = flow {
        val fakeList = listOf(
            Recipe(id = "1", name = "Pizza"),
            Recipe(id = "2", name = "Pasta")
        )
        emit(DataState.Success(fakeList))
    }

    override suspend fun getRecipesByFirstLetter(letter: String): Flow<DataState<List<Recipe>>> = flow {
        val fakeList = listOf(
            Recipe(id = "1", name = "Pizza"),
            Recipe(id = "2", name = "Pasta")
        )
        emit(DataState.Success(fakeList))
    }
}
