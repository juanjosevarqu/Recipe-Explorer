package com.varqulabs.recipexplorer.data.repository

import com.varqulabs.recipexplorer.core.domain.DataState
import com.varqulabs.recipexplorer.core.domain.fetchData
import com.varqulabs.recipexplorer.data.remote.MealsService
import com.varqulabs.recipexplorer.data.remote.dto.mapToModel
import com.varqulabs.recipexplorer.domain.model.Recipe
import com.varqulabs.recipexplorer.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow

class MealsRepositoryImpl(
    private val service: MealsService,
) : MealsRepository {

    override suspend fun getRecipeById(id: String): Flow<DataState<Recipe>> =
        fetchData { service.getRecipeById(id).body()?.recipes?.firstOrNull()?.mapToModel() }

    override suspend fun searchRecipesByName(name: String): Flow<DataState<List<Recipe>>> =
        fetchData { service.searchRecipesByName(name).body()?.recipes?.map { it.mapToModel() } }

    override suspend fun getRecipesByFirstLetter(letter: String): Flow<DataState<List<Recipe>>> =
        fetchData { service.searchRecipesByFirstLetter(letter).body()?.recipes?.map { it.mapToModel() } }

}