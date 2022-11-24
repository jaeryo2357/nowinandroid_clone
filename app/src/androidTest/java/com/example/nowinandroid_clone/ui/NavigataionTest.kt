package com.example.nowinandroid_clone.ui

/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import com.example.nowinandroid_clone.R
import com.example.nowinandroid_clone.MainActivity
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

/**
 * Tests all the navigation flows that are handled by the navigation library.
 */
@HiltAndroidTest
class NavigationTest {


    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @BindValue @get:Rule(order = 2)
    val tmpFolder: TemporaryFolder =
        TemporaryFolder.builder().assureDeletion().build()

    private lateinit var done: String
    private lateinit var navigateUp: String
    private lateinit var forYouLoading: String
    private lateinit var forYou: String
    private lateinit var episodes: String
    private lateinit var saved: String
    private lateinit var topics: String

    @Before
    fun setUp() {
        composeTestRule.activity.apply {
            done = getString(R.string.done)
            navigateUp = getString(R.string.navigate_up)
            forYouLoading = getString(R.string.for_you_loading)
            forYou = getString(R.string.for_you)
            episodes = getString(R.string.episodes)
            saved = getString(R.string.saved)
            topics = getString(R.string.following)
        }
    }

    @Test
    fun firstScreen_isForYou() {
        composeTestRule.apply {
            onNodeWithText("HEADLINES").assertExists()
        }
    }

    // TODO: implement tests related to navigation & resetting of destinations (b/213307564)
    /**
     * As per guidelines:
     *
     * When you select a navigation bar item (one that’s not currently selected), the app navigates
     * to that destination’s screen.
     *
     * Any prior user interactions and temporary screen states are reset, such as scroll position,
     * tab selection, and inline search.
     *
     * This default behavior can be overridden when needed to improve the user experience. For
     * example, an Android app that requires frequent switching between sections can preserve each
     * section’s state.
     */

    @Test
    fun navigationBar_navigateToPreviouslySelectedTab_restoreContent() {
        composeTestRule.apply {

            onNodeWithText("HEADLINES").performClick()

            onNodeWithText(topics).performClick()

            onNodeWithText(forYou).performClick()

            onNodeWithText("HEADLINES").assertIsOn()
        }
    }

    @Test
    fun navigationBar_reselectTab_keepsState() {
        composeTestRule.apply {
            onNodeWithText("HEADLINES").performClick()
            onNodeWithText(forYou).performClick()
            onNodeWithText("HEADLINES").assertIsOn()
        }
    }

    @Test
    fun topLevelDestinations_doNotShowUpArrow() {
        composeTestRule.apply {
            onNodeWithContentDescription(navigateUp).assertDoesNotExist()
            onNodeWithText(episodes).performClick()

            onNodeWithContentDescription(navigateUp).assertDoesNotExist()
            onNodeWithText(saved).performClick()

            onNodeWithContentDescription(navigateUp).assertDoesNotExist()
            onNodeWithText(topics).performClick()

            onNodeWithContentDescription(navigateUp).assertDoesNotExist()
        }
    }

    /*
     * There should always be at most one instance of a top-level destination at the same time.
     */
    @Test(expected = NoActivityResumedException::class)
    fun homeDestination_back_quitApp() {
        composeTestRule.apply {
            onNodeWithText(episodes).performClick()

            onNodeWithText(forYou).performClick()

            Espresso.pressBack()
        }
    }

    /*
     * When pressing back from any top level destination except "For you", the app navigates back
     * to the "For you" destination, no matter which destinations you visited in between.
     */
    @Test
    fun navigationBar_backFromAnyDestination_returnsToForYou() {
        composeTestRule.apply {

            onNodeWithText(episodes).performClick()

            onNodeWithText(topics).performClick()

            Espresso.pressBack()

            onNodeWithText("HEADLINES").assertExists()
        }
    }
}