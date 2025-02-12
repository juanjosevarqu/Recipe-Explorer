package com.varqulabs.recipexplorer.core.domain

sealed class DataState<out T> {

    data object Loading : DataState<Nothing>()

    data class Success<out T>(val data: T) : DataState<T>()

    data class Error(val message: String, val code: Int? = null) : DataState<Nothing>()

}

