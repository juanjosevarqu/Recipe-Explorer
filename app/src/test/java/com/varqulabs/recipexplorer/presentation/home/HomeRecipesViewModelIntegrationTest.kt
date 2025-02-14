package com.varqulabs.recipexplorer.presentation.home

import app.cash.turbine.test
import com.varqulabs.recipexplorer.data.remote.MealsService
import com.varqulabs.recipexplorer.data.remote.dto.RecipeDto
import com.varqulabs.recipexplorer.data.remote.dto.RecipesDto
import com.varqulabs.recipexplorer.data.repository.MealsRepositoryImpl
import com.varqulabs.recipexplorer.domain.repository.MealsRepository
import com.varqulabs.recipexplorer.domain.usecase.GetRecipesByFirstLetter
import com.varqulabs.recipexplorer.domain.usecase.GetRecipesByName
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesEvent.OnSearchRecipe
import junit.framework.TestCase.*
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class HomeRecipesViewModelIntegrationTest {

    private lateinit var viewModel: HomeRecipesViewModel
    private lateinit var repository: MealsRepository

    @Before
    fun setup() {
        val fakeService = object : MealsService {
            override suspend fun getRecipeById(id: String) = Response.success(
                RecipesDto(listOf(RecipeDto(id = "1", name = "Pizza", imageUrl = "", area = "Italian", instructions = "")))
            )

            override suspend fun searchRecipesByName(name: String) = Response.success(
                RecipesDto(listOf(RecipeDto(id = "2", name = "Pasta", imageUrl = "", area = "Italian", instructions = "")))
            )

            override suspend fun searchRecipesByFirstLetter(name: String) = Response.success(
                RecipesDto(listOf(RecipeDto(id = "1", name = "Pizza", imageUrl = "", area = "Italian", instructions = "")))
            )
        }

        repository = MealsRepositoryImpl(fakeService)

        val getRecipesByFirstLetter = GetRecipesByFirstLetter(repository)
        val getRecipesByName = GetRecipesByName(repository)
        viewModel = HomeRecipesViewModel(getRecipesByFirstLetter, getRecipesByName)
    }

    @Test
    fun `viewModel returns correct data from real repository`() = runTest {
        viewModel.uiState.test {
            val initialState = awaitItem()
            assertEquals(HomeRecipesState(), initialState)

            viewModel.eventHandler(OnSearchRecipe("P"))

            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)

            val successState = awaitItem()
            assertFalse(successState.isLoading)
            assertEquals(1, successState.recipesFiltered.size)
            assertEquals("Pizza", successState.recipesFiltered[0].name)

            cancelAndIgnoreRemainingEvents()
        }
    }
}