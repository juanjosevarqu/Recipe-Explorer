package com.varqulabs.recipexplorer.domain.usecase

import com.varqulabs.recipexplorer.domain.repository.MealsRepository

class GetRecipesByFirstLetter(
    private val mealsRepository: MealsRepository
) {
    suspend operator fun invoke(letter: String) = mealsRepository.getRecipesByFirstLetter(letter)
}