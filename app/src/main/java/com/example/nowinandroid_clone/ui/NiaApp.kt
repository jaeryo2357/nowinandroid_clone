package com.example.nowinandroid_clone.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Grid3x3
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Grid3x3
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.example.nowinandroid_clone.core.ui.ClearRippleTheme
import com.example.nowinandroid_clone.core.ui.theme.NiaTheme

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
                // TODO: Only show on small screens
                NiABottomBar(navigationActions, currentRoute)
            },
        ) { padding ->
            Surface(Modifier.fillMaxSize()) {
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
private fun NiABottomBar(
    navigationActions: NiaNavigationActions,
    currentRoute: String
) {
    // Wrap the navigation bar in a surface so the color behind the system
    // navigation is equal to the container color of the navigation bar.
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
                            navigationActions.navigateToTopLevelDestination(dst.route)
                        },
                        icon = {
                            Icon(
                                if (selected) dst.selectedIcon else dst.unselectedIcon,
                                contentDescription = null
                            )
                        },
                        label = { Text(stringResource(dst.iconTextId)) }
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