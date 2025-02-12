package com.varqulabs.recipexplorer.core.presentation.utils.modifier

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.delay

fun Modifier.clickableSingle(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    onClick: () -> Unit,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClick"] = onClick
    }
) {
    var isEnabled by remember { mutableStateOf(enabled) }
    val indication = ripple()

    LaunchedEffect(isEnabled) {
        if (!isEnabled) delay(450L)
        isEnabled = true
    }

    this@clickableSingle.clickable(
        enabled = isEnabled,
        onClickLabel = onClickLabel,
        onClick = { onClick(); isEnabled = false },
        indication = indication,
        interactionSource = remember { MutableInteractionSource() }
    )
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var componentSize by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition()
    val shimmerSlideTransition by transition.animateFloat(
        initialValue = 0f,
        targetValue = 2 * componentSize.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Reverse
        ),
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color.LightGray.copy(alpha = 0.6f),
                Color.White.copy(alpha = 0.45f),
                Color.LightGray.copy(alpha = 0.6f),
            ),
            start = Offset.Zero,
            end = Offset(x = shimmerSlideTransition, shimmerSlideTransition)
        )
    ).onGloballyPositioned { componentSize = it.size }
}