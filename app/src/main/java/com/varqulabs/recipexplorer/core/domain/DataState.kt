package com.varqulabs.recipexplorer.core.domain

sealed class DataState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T> : DataState<T>()
    class Success<T>(data: T) : DataState<T>(data)
    class Error<T>(message: String, data: T? = null) : DataState<T>(data, message)
}
