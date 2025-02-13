package com.varqulabs.recipexplorer.core.presentation.generics.images

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.varqulabs.recipexplorer.R
import com.varqulabs.recipexplorer.core.presentation.utils.modifier.shimmerEffect

@Composable
fun CoilImage(
    url: String,
    modifier: Modifier,
    contentDescription: String = "",
    defaultImage: Int = R.drawable.round_set_meal_24,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    shape: Shape = RoundedCornerShape(8.dp),
    colorFilter: ColorFilter? = null,
) {

    var showShimmer by rememberSaveable { mutableStateOf(true) }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .build(),
        contentDescription = contentDescription,
        error = painterResource(id = defaultImage),
        contentScale = contentScale,
        alignment = alignment,
        modifier = modifier
            .clip(shape)
            .then(
                if (showShimmer) Modifier.shimmerEffect()
                else Modifier
            ),
        onLoading = { showShimmer = true },
        onSuccess = { showShimmer = false },
        onError = { showShimmer = false },
        colorFilter = colorFilter,
    )
}