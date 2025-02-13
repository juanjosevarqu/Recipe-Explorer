package com.varqulabs.recipexplorer.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.varqulabs.recipexplorer.core.domain.DataState
import com.varqulabs.recipexplorer.core.presentation.utils.mvi.MVIContract
import com.varqulabs.recipexplorer.core.presentation.utils.mvi.MVIDelegate
import com.varqulabs.recipexplorer.domain.usecase.GetRecipeById
import com.varqulabs.recipexplorer.navigation.Routes
import com.varqulabs.recipexplorer.presentation.detail.RecipeDetailEvent.Init
import com.varqulabs.recipexplorer.presentation.detail.RecipeDetailEvent.Loading
import com.varqulabs.recipexplorer.presentation.detail.RecipeDetailEvent.OnBack
import com.varqulabs.recipexplorer.presentation.detail.RecipeDetailEvent.OnOpenMapLocation
import com.varqulabs.recipexplorer.presentation.detail.RecipeDetailUiEffect.GoBack
import com.varqulabs.recipexplorer.presentation.detail.RecipeDetailUiEffect.NavigateToRecipeLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeById: GetRecipeById,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(), MVIContract<RecipeDetailState, RecipeDetailEvent, RecipeDetailUiEffect> by MVIDelegate(
    RecipeDetailState(recipeId = savedStateHandle.toRoute<Routes.RecipeDetail>().recipeId)
) {

    private val recipeId get() = uiState.value.recipeId

    override fun eventHandler(event: RecipeDetailEvent) {
        when (event) {
            is Loading -> updateUi { copy(isLoading = event.isLoading) }
            is OnBack -> emitNavigationBack()
            is OnOpenMapLocation -> emitNavigationToMap(event.areaName)
            is Init -> getRecipeDetailById(recipeId)
        }
    }

    private fun emitNavigationBack() = viewModelScope.emitEffect(GoBack)

    private fun emitNavigationToMap(
        areaName: String
    ) = viewModelScope.emitEffect(NavigateToRecipeLocation(areaName))

    private fun getRecipeDetailById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getRecipeById(id).collectLatest { dataState ->
                updateUi {
                    when (dataState) {
                        is DataState.Loading -> copy(isLoading = true)
                        is DataState.Success -> copy(recipeDetail = dataState.data, isLoading = false).also { disableReload() }
                        is DataState.Error -> copy(errorMsg = dataState.message, isLoading = false)
                    }
                }
            }
        }
    }

    private fun disableReload() = updateUi { copy(reload = false) }

}