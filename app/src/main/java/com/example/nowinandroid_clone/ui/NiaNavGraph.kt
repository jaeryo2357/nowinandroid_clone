package com.example.nowinandroid_clone.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nowinandroid_clone.ui.foryou.ForYouRoute

@Composable
fun NiaNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NiaDestinations.FOR_YOU_ROUTE
) {
   NavHost(
       navController = navController,
       startDestination = startDestination
   )  {
       composable(NiaDestinations.FOR_YOU_ROUTE) {
           ForYouRoute(modifier = modifier.testTag("FOR YOU"))
       }
       composable(NiaDestinations.EPISODES_ROUTE) {
           Text("EPISODES", modifier)
       }
       composable(NiaDestinations.SAVED_ROUTE) {
           Text("SAVED", modifier)
       }
       composable(NiaDestinations.TOPICS_ROUTE) {
           Text("TOPICS", modifier)
       }
   }
}