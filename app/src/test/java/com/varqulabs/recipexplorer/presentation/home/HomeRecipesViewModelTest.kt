package com.varqulabs.recipexplorer.presentation.home

import app.cash.turbine.test
import com.varqulabs.recipexplorer.domain.model.Recipe
import com.varqulabs.recipexplorer.domain.usecase.GetRecipesByFirstLetter
import com.varqulabs.recipexplorer.domain.usecase.GetRecipesByName
import com.varqulabs.recipexplorer.fakes.FakeMealsRepository
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesEvent.Init
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesEvent.OnSearchRecipe
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HomeRecipesViewModelTest {

    private lateinit var fakeMealRepository: FakeMealsRepository
    private lateinit var viewModel: HomeRecipesViewModel

    @Before
    fun setUp() {
        fakeMealRepository = FakeMealsRepository().apply {
            recipesByFirstLetter = listOf(
                Recipe(
                    id = "1",
                    name = "Pizza",
                    imageUrl = "https://fakeimg.pl/300/",
                    instructions = "Preparar la pizza",
                    area = "Italian"
                ),
                Recipe(
                    id = "2",
                    name = "Hamburguesa",
                    imageUrl = "https://fakeimg.pl/300/",
                    instructions = "Preparar la hamburguesa",
                    area = "American"
                ),
            )
            recipesBySearch = listOf(
                Recipe(
                    id = "3",
                    name = "Pasta",
                    imageUrl = "https://fakeimg.pl/300/",
                    instructions = "Preparing the pasta",
                    area = "Mexican"
                ),
                Recipe(
                    id = "4",
                    name = "Burger",
                    imageUrl = "https://fakeimg.pl/300/",
                    instructions = "Cocinar la Burger",
                    area = "Spanish"
                ),
            )
        }
        val getRecipesByFirstLetter = GetRecipesByFirstLetter(fakeMealRepository)
        val getRecipesByName = GetRecipesByName(fakeMealRepository)
        viewModel = HomeRecipesViewModel(getRecipesByFirstLetter, getRecipesByName)
    }

    @Test
    fun `when searching by a single letter emits Loading and then Success in uiState`() = runTest {
        viewModel.uiState.test {
            val initialState = awaitItem()
            assertEquals(HomeRecipesState(), initialState)

            viewModel.eventHandler(Init)

            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)

            val successState = awaitItem()
            assertFalse(successState.isLoading)
            assertEquals(fakeMealRepository.recipesByFirstLetter, successState.recipesFiltered)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when repository returns error emits Loading and then Error in uiState`() = runTest {
        fakeMealRepository.shouldReturnError = true

        viewModel.uiState.test {
            val initialState = awaitItem()
            assertEquals(HomeRecipesState(), initialState)

            viewModel.eventHandler(OnSearchRecipe("P"))

            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)

            val errorState = awaitItem()
            assertFalse(errorState.isLoading)
            assertNotNull(errorState.errorMsg)
            assertEquals("Error de conexión", errorState.errorMsg)

            cancelAndIgnoreRemainingEvents()
        }
    }
}