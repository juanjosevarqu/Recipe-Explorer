package com.varqulabs.recipexplorer.domain.usecase

import com.varqulabs.recipexplorer.domain.repository.MealsRepository

class GetRecipesByName(
    private val mealsRepository: MealsRepository
) {
    suspend operator fun invoke(name: String) = mealsRepository.searchRecipesByName(name)
}