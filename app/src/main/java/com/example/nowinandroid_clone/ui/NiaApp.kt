package com.example.nowinandroid_clone.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nowinandroid_clone.R
import com.example.nowinandroid_clone.ui.theme.NiaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NiaApp() {
    NiaTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            NiaNavigationActions(navController)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: NiaDestinations.FOR_YOU_ROUTE

        Scaffold(
            modifier = Modifier,
            bottomBar = {
                NiaBottomBar(navigationActions, currentRoute)
            }
        ) { padding ->
            Surface(Modifier.fillMaxWidth()) {
                NiaNavGraph(
                    navController = navController,
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(padding)
                )
            }
        }
    }
}

@Composable
private fun NiaBottomBar(
    navigationActions: NiaNavigationActions,
    currentRoute: String
) {
    Surface(color = MaterialTheme.colorScheme.surface) {
        CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
            NavigationBar(
                modifier = Modifier
                    .navigationBarsPadding()
                    .captionBarPadding(),
                tonalElevation = 0.dp
            ) {
                TOP_LEVEL_DESTINATIONS.forEach { dst ->
                    val selected = currentRoute == dst.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navigationActions.navigationToTopLevelDestination(dst.route)
                        },
                        icon = {
                            Icon(
                                if (selected) dst.selectedIcon else dst.unselectedIcon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(stringResource(dst.iconTextId))
                        }
                    )
                }
            }
        }
    }
}

private sealed class Destination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int
) {
    object ForYou : Destination(
        route = NiaDestinations.FOR_YOU_ROUTE,
        selectedIcon = Icons.Filled.Upcoming,
        unselectedIcon = Icons.Outlined.Upcoming,
        iconTextId = R.string.for_you
    )

    object Episodes : Destination(
        route = NiaDestinations.EPISODES_ROUTE,
        selectedIcon = Icons.Filled.AutoStories,
        unselectedIcon = Icons.Outlined.AutoStories,
        iconTextId = R.string.episodes
    )

    object Saved : Destination(
        route = NiaDestinations.SAVED_ROUTE,
        selectedIcon = Icons.Filled.Bookmarks,
        unselectedIcon = Icons.Outlined.Bookmarks,
        iconTextId = R.string.saved
    )

    object Following : Destination(
        route = NiaDestinations.FOLLOWING_ROUTE,
        selectedIcon = Icons.Filled.Grid3x3,
        unselectedIcon = Icons.Outlined.Grid3x3,
        iconTextId = R.string.following
    )
}

private val TOP_LEVEL_DESTINATIONS = listOf(
    Destination.ForYou,
    Destination.Episodes,
    Destination.Saved,
    Destination.Following
)