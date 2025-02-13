package com.varqulabs.recipexplorer.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.varqulabs.recipexplorer.domain.model.Recipe

@Composable
fun RecipeCardInfo(
    modifier: Modifier = Modifier,
    recipe: Recipe,
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(50.dp),
        shape = RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = recipe.name,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Instrucciones:",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = recipe.instructions,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}