package com.varqulabs.recipexplorer.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.varqulabs.recipexplorer.core.presentation.generics.top_bars.DefaultAppBar
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesEvent.Init
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesEvent.Loading
import com.varqulabs.recipexplorer.presentation.home.HomeRecipesEvent.OnClickRecipe
import com.varqulabs.recipexplorer.presentation.home.components.RecipeItem
import com.varqulabs.recipexplorer.presentation.home.components.RecipeItemShimmer

@Composable
fun HomeRecipesScreen(
    state: HomeRecipesState,
    eventHandler: (HomeRecipesEvent) -> Unit
) {

    LaunchedEffect(state.reload) { if (state.reload) eventHandler(Init) }

    LaunchedEffect(state.isLoading) { eventHandler(Loading(state.isLoading)) }

    Scaffold(
       topBar = {
            DefaultAppBar(
                title = "Recipe Xplorer",
                navigationIcon = null,
            ) { }
       }
   ) { paddingValues ->
       LazyColumn(
           modifier = Modifier
               .padding(paddingValues)
               .padding(horizontal = 8.dp)
           ,
           verticalArrangement = Arrangement.spacedBy(12.dp)
       ) {

           if (state.recipes.isEmpty()) {
               items(9) {
                   RecipeItemShimmer(Modifier.fillMaxWidth())
               }
           } else {
               items(state.recipes) { recipe ->
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
}