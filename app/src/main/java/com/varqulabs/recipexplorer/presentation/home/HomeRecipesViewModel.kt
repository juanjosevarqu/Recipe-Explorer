package com.varqulabs.recipexplorer.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.recipexplorer.core.domain.DataState
import com.varqulabs.recipexplorer.core.presentation.utils.mvi.MVIContract
import com.varqulabs.recipexplorer.core.presentation.utils.mvi.MVIDelegate
import com.varqulabs.recipexplorer.domain.usecase.GetRecipesByFirstLetter
import com.varqulabs.recipexplorer.domain.usecase.GetRecipesByName
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesEvent.Init
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesEvent.Loading
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesEvent.OnClickRecipe
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesEvent.OnSearchRecipe
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesUiEffect.NavigateToRecipeDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeRecipesViewModel @Inject constructor(
    private val getRecipesByFirstLetter: GetRecipesByFirstLetter,
    private val getRecipesByName: GetRecipesByName
) : ViewModel(), MVIContract<HomeRecipesState, HomeRecipesEvent, HomeRecipesUiEffect> by MVIDelegate(HomeRecipesState()) {

    override fun eventHandler(event: HomeRecipesEvent) {
        when (event) {
            is Loading -> updateUi { copy(isLoading = event.isLoading) }
            is OnClickRecipe -> emitNavigationToDetail(event.recipeId)
            is Init -> searchRecipesByFirstLetterInit("c")
            is OnSearchRecipe -> {
                if (event.query.length == 1) searchRecipesByFirstLetter(event.query)
                else searchRecipesByName(event.query)
            }
        }
    }

    private fun emitNavigationToDetail(recipeId: String) = viewModelScope.emitEffect(NavigateToRecipeDetail(recipeId))

    private fun searchRecipesByFirstLetterInit(letter: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getRecipesByFirstLetter(letter).collectLatest { data ->
                updateUi {
                    when (data) {
                        is DataState.Loading -> copy(isLoading = true)
                        is DataState.Success -> copy(recipes = data.data).also { disableReload() }
                        is DataState.Error -> copy(errorMsg = data.message)
                    }
                }
            }
        }
    }

    private fun searchRecipesByFirstLetter(letter: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getRecipesByFirstLetter(letter).collectLatest { data ->
                updateUi {
                    when (data) {
                        is DataState.Loading -> copy(isLoading = true)
                        is DataState.Success -> copy(recipesFiltered = data.data)
                        is DataState.Error -> copy(errorMsg = data.message)
                    }
                }
            }
        }
    }

    private fun searchRecipesByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getRecipesByName(name).collectLatest { data ->
                updateUi {
                    when (data) {
                        is DataState.Loading -> copy(isLoading = true)
                        is DataState.Success -> copy(recipesFiltered = data.data)
                        is DataState.Error -> copy(errorMsg = data.message)
                    }
                }
            }
        }
    }

    private fun disableReload() = updateUi { copy(reload = false) }

}