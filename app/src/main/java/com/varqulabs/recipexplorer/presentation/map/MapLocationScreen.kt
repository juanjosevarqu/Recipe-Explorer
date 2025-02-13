package com.varqulabs.recipexplorer.presentation.map

import android.content.res.Resources
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.varqulabs.recipexplorer.R
import com.varqulabs.recipexplorer.core.presentation.generics.top_bars.DefaultAppBar

@Composable
fun MapLocationScreen(
    latitude: Double,
    longitude: Double,
    onBack: () -> Unit
) {

    val context = LocalContext.current

    val uiSettings by remember { mutableStateOf(MapUiSettings()) }
    val mapStyleOptions = remember {
        try {
            MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style)
        } catch (e: Resources.NotFoundException) {
            null
        }
    }
    val properties by remember { mutableStateOf(MapProperties(mapStyleOptions = mapStyleOptions)) }

    val initialPosition = remember { LatLng(latitude, longitude) }
    val markerState = remember { MarkerState(position = initialPosition) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialPosition, 6.5f)
    }

    Scaffold(
        topBar = {
            DefaultAppBar(
                title = "Origen de la receta",
                onBack = onBack,
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxWidth(),
                properties = properties,
                uiSettings = uiSettings,
                cameraPositionState = cameraPositionState
            ) {
                AdvancedMarker(
                    state = markerState,
                )
            }
        }
    }
}