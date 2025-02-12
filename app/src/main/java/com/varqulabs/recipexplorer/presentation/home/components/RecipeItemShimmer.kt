package com.varqulabs.recipexplorer.presentation.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
                vertical = 12.dp,
                horizontal = 16.dp,
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shimmerEffect()
            )

            RecipeInfoShimmer(Modifier.fillMaxWidth())
        }

    }
}

@Composable
private fun RecipeInfoShimmer(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth(0.5f)
                .clip(RoundedCornerShape(8.dp))
                .shimmerEffect()
        )

        Box(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(8.dp))
                .shimmerEffect()
        )
    }
}