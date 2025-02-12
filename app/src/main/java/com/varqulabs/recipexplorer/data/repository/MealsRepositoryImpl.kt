package com.varqulabs.recipexplorer.data.repository

import com.varqulabs.recipexplorer.core.domain.DataState
import com.varqulabs.recipexplorer.data.remote.MealDbService
import com.varqulabs.recipexplorer.data.remote.dto.mapToModel
import com.varqulabs.recipexplorer.domain.model.Recipe
import com.varqulabs.recipexplorer.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class MealsRepositoryImpl(
    private val service: MealDbService,
) : MealsRepository {

    override suspend fun getRecipeById(id: String): Flow<DataState<Recipe>> =
        fetchData { service.getRecipeById(id).body()?.recipes?.firstOrNull()?.mapToModel() }

    override suspend fun searchRecipesByName(name: String): Flow<DataState<List<Recipe>>> =
        fetchData { service.searchRecipesByName(name).body()?.recipes?.map { it.mapToModel() } }

    override suspend fun getRecipesByIngredient(ingredient: String): Flow<DataState<List<Recipe>>> =
        fetchData { service.getRecipesByIngredient(ingredient).body()?.recipes?.map { it.mapToModel() } }

}

private fun <T> fetchData(apiCall: suspend () -> T?): Flow<DataState<T>> = flow {
    emit(DataState.Loading)
    try {
        val result = apiCall() ?: throw Exception("No se encontraron resultados")
        emit(DataState.Success(result))
    } catch (e: IOException) {
        emit(DataState.Error("Error de conexión. Verifica tu internet."))
    } catch (e: Exception) {
        emit(DataState.Error("Error: ${e.localizedMessage ?: "Desconocido"}"))
    }
}