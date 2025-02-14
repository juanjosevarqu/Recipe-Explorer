package com.varqulabs.recipexplorer.presentation.detail.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varqulabs.recipexplorer.R

@Composable
fun OriginFloatingButton(
    expanded: Boolean,
    text: String,
    onClick: () -> Unit,
) {
    ExtendedFloatingActionButton(
        expanded = expanded,
        text = {
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.baseline_location_pin_24),
                contentDescription = "Abrir mapa",
                modifier = Modifier.size(32.dp)
            )
        },
        onClick = onClick,
    )
}