package com.example.nowinandroid_clone.ui.foryou

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import com.example.nowinandroid_clone.R
import com.example.nowinandroid_clone.data.news.Topic

class ForYouScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun circularProgressIndicator_whenScreenIsLoading_exists() {
        composeTestRule.setContent {
            ForYouScreen(
                uiState = ForYouFeedUiState.Loading,
                onTopicCheckedChanged = { _, _ -> },
                saveFollowedTopics = {}
            )
        }

        composeTestRule.onNodeWithContentDescription(
            composeTestRule.activity.resources.getString(R.string.for_you_loading)
        ).assertExists()
    }

    @Test
    fun topicSelector_whenNoTopicsSelected_showsTopicChipsAndDisabledDoneButton() {
        composeTestRule.setContent {
            ForYouScreen(
                uiState = ForYouFeedUiState.PopulatedFeed.FeedWithTopicSelection(
                    selectedTopics = listOf(
                        Topic(
                            id = 0,
                            name = "Headlines",
                            description = ""
                        ) to false,
                        Topic(
                            id = 1,
                            name = "UI",
                            description = ""
                        ) to false,
                        Topic(
                            id = 2,
                            name = "Tools",
                            description = ""
                        ) to false
                    ),
                    feed = emptyList()
                ),
                onTopicCheckedChanged = { _, _ -> },
                saveFollowedTopics = {}
            )
        }
        
        composeTestRule
            .onNodeWithText("HEADLINES")
            .assertIsDisplayed()
            .assertHasClickAction()

        composeTestRule
            .onNodeWithText("UI")
            .assertIsDisplayed()
            .assertHasClickAction()

        composeTestRule
            .onNodeWithText("TOOLS")
            .assertIsDisplayed()
            .assertHasClickAction()

        composeTestRule
            .onNodeWithText(
                composeTestRule.activity.resources.getString(R.string.done)
            ).assertIsDisplayed()
            .assertIsNotEnabled()
            .assertHasClickAction()
    }

    @Test
    fun topicSelector_whenSomeTopicsSelected_showsTopicChipsAndEnabledDoneButton() {
        composeTestRule.setContent {
            ForYouScreen(
                uiState = ForYouFeedUiState.PopulatedFeed.FeedWithTopicSelection(
                    selectedTopics = listOf(
                        Topic(
                            id = 0,
                            name = "Headlines",
                            description = ""
                        ) to false,
                        Topic(
                            id = 1,
                            name = "UI",
                            description = ""
                        ) to true,
                        Topic(
                            id = 2,
                            name = "Tools",
                            description = ""
                        ) to false
                    ),
                    feed = emptyList()
                ),
                onTopicCheckedChanged = { _, _ -> },
                saveFollowedTopics = {}
            )
        }

        composeTestRule
            .onNodeWithText("HEADLINES")
            .assertIsDisplayed()
            // .assertIsOff()
            .assertHasClickAction()

        composeTestRule
            .onNodeWithText("UI")
            .assertIsDisplayed()
            // .assertIsOn()
            .assertHasClickAction()

        composeTestRule
            .onNodeWithText("TOOLS")
            .assertIsDisplayed()
            // .assertIsOff()
            .assertHasClickAction()

        composeTestRule
            .onNodeWithText(composeTestRule.activity.resources.getString(R.string.done))
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertHasClickAction()
    }
}