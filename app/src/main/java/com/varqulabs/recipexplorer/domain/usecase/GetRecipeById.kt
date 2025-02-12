package com.varqulabs.recipexplorer.domain.usecase

import com.varqulabs.recipexplorer.domain.repository.MealsRepository

class GetRecipeById(
    private val mealsRepository: MealsRepository
) {
    suspend operator fun invoke(id: String) = mealsRepository.getRecipeById(id)
}