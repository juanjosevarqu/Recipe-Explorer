package com.varqulabs.recipexplorer.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.varqulabs.recipexplorer.core.presentation.generics.top_bars.DefaultAppBar
import com.varqulabs.recipexplorer.core.presentation.utils.composables.screenHeight
import com.varqulabs.recipexplorer.core.presentation.utils.modifier.clickableSingle
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesEvent.Init
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesEvent.Loading
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesEvent.OnClickRecipe
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesEvent.OnSearchRecipe
import com.varqulabs.recipexplorer.presentation.home.components.RecipeItem
import com.varqulabs.recipexplorer.presentation.home.components.RecipeItemShimmer
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRecipesScreen(
    state: HomeRecipesState,
    eventHandler: (HomeRecipesEvent) -> Unit
) {

    val focusManager = LocalFocusManager.current

    LaunchedEffect(state.reload) { if (state.reload) eventHandler(Init) }

    LaunchedEffect(state.isLoading) { eventHandler(Loading(state.isLoading)) }

    var querySearch by remember { mutableStateOf("") }
    var isActive by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(querySearch) {
        if (querySearch.isNotEmpty()) {
            delay(350L)
            eventHandler(OnSearchRecipe(querySearch))
        }
    }

    Scaffold(
        topBar = {
            DefaultAppBar(
                title = "Explorador de Recetas",
                navigationIcon = null,
            ) { }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 8.dp)
                .fillMaxSize()
        ) {

            DockedSearchBar(
                inputField = {
                    SearchBarDefaults.InputField(
                        query = querySearch,
                        onQueryChange = { querySearch = it },
                        onSearch = { focusManager.clearFocus() },
                        expanded = isActive,
                        onExpandedChange = { isActive = it },
                        placeholder = { Text("Busca el nombre de una receta") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        trailingIcon =
                        when {
                            isActive && querySearch.isEmpty() -> { {
                                    TextButton(
                                        onClick = { isActive = false }
                                    ) {
                                        Text("Cerrar")
                                    }
                                } }
                            querySearch.isNotEmpty() -> { {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = null,
                                        modifier = Modifier.clickableSingle {
                                            querySearch = ""
                                            eventHandler(OnSearchRecipe(""))
                                        }
                                    )
                                } }
                            else -> null
                        },
                    )
                },
                expanded = isActive,
                onExpandedChange = { isActive = it },
                modifier = Modifier.fillMaxWidth(),
            ) {
                SearchRecipeContent(
                    modifier = Modifier,
                    state = state,
                    eventHandler = eventHandler
                )
            }

            if (!isActive) {
                HomeRecipesContent(
                    modifier = Modifier,
                    paddingValues = paddingValues,
                    state = state,
                    eventHandler = eventHandler
                )
            }
        }
    }
}

@Composable
private fun HomeRecipesContent(
    modifier: Modifier,
    paddingValues: PaddingValues,
    state: HomeRecipesState,
    eventHandler: (HomeRecipesEvent) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = paddingValues.calculateTopPadding() / 1.6f
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (state.recipes.isEmpty()) {
            items(9) {
                RecipeItemShimmer(Modifier.fillMaxWidth())
            }
        } else {
            items(state.recipes, key = { it.id }) { recipe ->
                RecipeItem(
                    name = recipe.name,
                    imageUrl = recipe.imageUrl,
                    description = recipe.instructions
                ) {
                    eventHandler(OnClickRecipe(recipe.id))
                }
            }
        }
    }
}

@Composable
private fun SearchRecipeContent(
    modifier: Modifier,
    state: HomeRecipesState,
    eventHandler: (HomeRecipesEvent) -> Unit
) {
    when {
        state.isLoading && state.recipesFiltered.isEmpty() -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(max = screenHeight()),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(
                    horizontal = 8.dp,
                    vertical = 12.dp,
                )
            ) {
                items(4, key = { it.hashCode() }) {
                    RecipeItemShimmer(
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
        state.recipesFiltered.isNotEmpty() -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(max = screenHeight()),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(
                    horizontal = 8.dp,
                    vertical = 12.dp,
                )
            ) {
                items(state.recipesFiltered, key = { it.hashCode() }) {
                    RecipeItem(
                        modifier = Modifier.fillMaxWidth(),
                        name = it.name,
                        imageUrl = it.imageUrl,
                        description = it.instructions
                    ) {
                        eventHandler(OnClickRecipe(it.id))
                    }
                }
            }
        }
    }
}