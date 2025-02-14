package com.varqulabs.recipexplorer.presentation.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.varqulabs.recipexplorer.core.presentation.utils.modifier.shimmerEffect

@Composable
fun RecipeItemShimmer(
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Row(
        modifier = modifier
            .border(
                width = 1.5.dp,
                color = Color.DarkGray,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp,
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        Box(
            modifier = Modifier
                .height(180.dp)
                .weight(1f)
                .clip(RoundedCornerShape(8.dp))
                .shimmerEffect()
        )
    }
}