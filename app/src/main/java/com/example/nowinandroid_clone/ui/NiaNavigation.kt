package com.example.nowinandroid_clone.ui

import androidx.navigation.NavController

object NiaDestinations {
    const val FOR_YOU_ROUTE = "for_you"
    const val EPISODES_ROUTE = "episodes"
    const val SAVED_ROUTE = "saved"
    const val TOPICS_ROUTE = "topics"
}

class NiaNavigationActions(private val navController: NavController) {
    fun navigationToTopLevelDestination(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            navController.graph.startDestinationRoute?.let {
                popUpTo(it)
            }
        }
    }
}