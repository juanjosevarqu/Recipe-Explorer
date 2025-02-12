package com.varqulabs.recipexplorer.core.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

sealed class DataState<out T> {

    data object Loading : DataState<Nothing>()

    data class Success<out T>(val data: T) : DataState<T>()

    data class Error(val message: String, val code: Int? = null) : DataState<Nothing>()

}

fun <T> fetchData(apiCall: suspend () -> T?): Flow<DataState<T>> = flow {
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

