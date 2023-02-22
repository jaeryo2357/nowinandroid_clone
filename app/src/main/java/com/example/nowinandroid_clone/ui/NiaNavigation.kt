package com.example.nowinandroid_clone.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

object NiaDestinations {
    const val FOR_YOU_ROUTE = "for_you"
    const val EPISODES_ROUTE = "episodes"
    const val SAVED_ROUTE = "saved"
    const val FOLLOWING_ROUTE = "following"
    const val TOPICS_ROUTE = "topic"
}

class NiaNavigationActions(private val navController: NavController) {
    fun navigateToTopLevelDestination(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}