package com.varqulabs.recipexplorer.domain.model

data class Recipe(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val area: String = "",
    val instructions: String = "",
)
