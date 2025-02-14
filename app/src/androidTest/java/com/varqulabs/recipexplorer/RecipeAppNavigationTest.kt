package com.varqulabs.recipexplorer

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.varqulabs.recipexplorer.di.ApiModule
import com.varqulabs.recipexplorer.di.RepositoryModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(RepositoryModule::class, ApiModule::class)
class RecipeAppNavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun verify_navigation_from_home_to_detail_to_map_and_back() {
        composeTestRule.onNodeWithText("Explorador de Recetas").assertIsDisplayed()

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodes(hasContentDescription("Recipe item", substring = true))
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule
            .onAllNodes(hasContentDescription("Recipe item", substring = true))
            .onFirst()
            .performClick()

        composeTestRule.onNodeWithText("Detalles de la receta").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Abrir mapa").performClick()
        composeTestRule.onNodeWithText("Origen de la receta").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Volver atrás").performClick()
        composeTestRule.onNodeWithText("Detalles de la receta").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Volver atrás").performClick()
        composeTestRule.onNodeWithText("Explorador de Recetas").assertIsDisplayed()
    }
}