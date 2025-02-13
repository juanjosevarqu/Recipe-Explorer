package com.varqulabs.recipexplorer.navigation.utils

import androidx.navigation.NavHostController
import com.varqulabs.recipexplorer.navigation.Routes

fun NavHostController.navigateTo(route: Routes) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
}