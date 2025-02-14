package com.varqulabs.recipexplorer.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.varqulabs.recipexplorer.core.presentation.generics.images.CoilImage
import com.varqulabs.recipexplorer.core.presentation.generics.top_bars.DefaultAppBar
import com.varqulabs.recipexplorer.core.presentation.utils.modifier.shimmerEffect
import com.varqulabs.recipexplorer.presentation.detail.RecipeDetailEvent.OnBack
import com.varqulabs.recipexplorer.presentation.detail.RecipeDetailEvent.OnOpenMapLocation
import com.varqulabs.recipexplorer.presentation.detail.components.OriginFloatingButton
import com.varqulabs.recipexplorer.presentation.detail.components.RecipeCardInfo

@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    eventHandler: (RecipeDetailEvent) -> Unit,
) {

    var expandedFAB by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        topBar = {
            DefaultAppBar(
                title = "Detalles de la receta",
                onBack = { eventHandler(OnBack) }
            )
        },
        floatingActionButton = {
            OriginFloatingButton(
                expanded = expandedFAB,
                text = "País de origen",
                onClick = {
                    if (expandedFAB) {
                        expandedFAB = false
                        eventHandler(OnOpenMapLocation(state.recipeDetail.area))
                    } else {
                        expandedFAB = true
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {

            if (state.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .shimmerEffect(),
                    )
                }
            } else {
                item {
                    CoilImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = 6.dp),
                        url = state.recipeDetail.imageUrl,
                        shape = RoundedCornerShape(0.dp),
                        contentScale = ContentScale.Crop,
                    )
                }

                item {
                    RecipeCardInfo(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = 6.dp),
                        recipe = state.recipeDetail,
                    )
                }
            }
        }
    }
}