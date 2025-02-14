package com.varqulabs.recipexplorer.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.varqulabs.recipexplorer.core.presentation.generics.images.CoilImage
import com.varqulabs.recipexplorer.core.presentation.utils.modifier.clickableSingle

@Composable
fun RecipeItem(
    name: String,
    description: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .border(
                width = 1.5.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(24.dp)
            )
            .background(MaterialTheme.colorScheme.onSecondary, shape = RoundedCornerShape(24.dp))
            .clickableSingle(onClick = onClick)
            .padding(14.dp)
            .semantics { contentDescription = "Recipe item" },
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        CoilImage(
            url = imageUrl,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = name,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        ItemDescription(
            modifier = Modifier.fillMaxWidth(),
            description = description,
        )
    }
}

@Composable
private fun ItemDescription(
    modifier: Modifier = Modifier,
    description: String,
) {
    Row(
        modifier = modifier,
    ) {

        Text(
            text = description,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.labelMedium
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Ir a mas detalles de la receta",
            modifier = Modifier.size(32.dp)
        )
    }
}