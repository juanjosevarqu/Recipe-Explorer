package com.varqulabs.recipexplorer

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.varqulabs.recipexplorer.navigation.AppNavGraph
import com.varqulabs.recipexplorer.ui.theme.RecipeExplorerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeExplorerTheme {

                val navController = rememberNavController()

                AppNavGraph(
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
            super.attachBaseContext(newBase?.limitFontScale())
    }
}

const val MAX_FONT_SCALE = 1.10F
const val MAX_DPI_SCALE = 1.05F

fun Context.limitFontScale(
    maxFontScale: Float = MAX_FONT_SCALE,
    maxDpiScale: Float = MAX_DPI_SCALE,
): Context {
    val configuration = resources.configuration
    val defaultDeviceDensity = DisplayMetrics.DENSITY_DEVICE_STABLE
    val maximumDpiScale = (defaultDeviceDensity * maxDpiScale).toInt()

    val exceedsFontScale = configuration.fontScale > maxFontScale
    val exceedsDpiScale = configuration.densityDpi < DisplayMetrics.DENSITY_560 && configuration.densityDpi > maximumDpiScale

    configuration.apply {
        if (exceedsFontScale) { fontScale = maxFontScale }
        if (exceedsDpiScale) { densityDpi = maximumDpiScale }
    }

    return if (exceedsFontScale || exceedsDpiScale) createConfigurationContext(configuration)
    else this
}
