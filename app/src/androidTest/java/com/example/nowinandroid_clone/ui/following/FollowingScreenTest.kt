package com.example.nowinandroid_clone.ui.following

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Before
import org.junit.Rule
import com.example.nowinandroid_clone.R
import com.example.nowinandroid_clone.data.model.Topic
import org.junit.Test

class FollowingScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var followingLoading: String
    private lateinit var followingErrorHeader: String
    private lateinit var followingTopicCardIcon: String
    private lateinit var followingTopicCardFollowButton: String
    private lateinit var followingTopicCardUnfollowButton: String

    @Before
    fun setup() {
        composeTestRule.activity.apply {
            followingLoading = getString(R.string.following_loading)
            followingErrorHeader = getString(R.string.following_error_header)
            followingTopicCardIcon = getString(R.string.following_topic_card_icon_content_desc)
            followingTopicCardFollowButton =
                getString(R.string.following_topic_card_follow_button_content_desc)
            followingTopicCardUnfollowButton =
                getString(R.string.following_topic_card_unfollow_button_content_desc)
        }
    }

    @Test
    fun niaLoadingIndicator_whenScreenIsLoading_showLoading() {
        composeTestRule.setContent {
            FollowingScreen(
                uiState = FollowingUiState.Loading,
                followTopic = { _, _ -> },
                navigateToTopic = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(followingLoading)
            .assertExists()
    }

    @Test
    fun followingWithTopics_whenTopicsFollowed_showFollowedAndUnfollowedTopicsWithInfo() {
        composeTestRule.setContent {
            FollowingScreen(
                uiState = FollowingUiState.Topics(topics = testTopics),
                followTopic = { _, _ -> },
                navigateToTopic = {}
            )
        }

        composeTestRule
            .onNodeWithText(TOPIC_1_NAME)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(TOPIC_2_NAME)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(TOPIC_3_NAME)
            .assertIsDisplayed()

        composeTestRule
            .onAllNodesWithText(TOPIC_DESC)
            .assertCountEquals(testTopics.count())

        composeTestRule
            .onAllNodesWithContentDescription(followingTopicCardIcon)
            .assertCountEquals(testTopics.count())

        composeTestRule
            .onAllNodesWithContentDescription(followingTopicCardFollowButton)
            .assertCountEquals(numberOfUnfollowedTopics)

        composeTestRule
            .onAllNodesWithContentDescription(followingTopicCardUnfollowButton)
            .assertCountEquals(testTopics.filter { it.followed }.size)
    }

    @Test
    fun followingError_whenErrorOccurs_thenShowEmptyErrorScreen() {
        composeTestRule.setContent {
            FollowingScreen(
                uiState = FollowingUiState.Error,
                followTopic = { _, _ -> },
                navigateToTopic = {}
            )
        }

        composeTestRule
            .onNodeWithText(followingErrorHeader)
            .assertIsDisplayed()
    }
}

private const val TOPIC_1_NAME = "Headlines"
private const val TOPIC_2_NAME = "UI"
private const val TOPIC_3_NAME = "Tools"
private const val TOPIC_DESC = "At vero eos et accusamus et iusto odio dignissimos ducimus qui."

private val testTopics = listOf(
    Topic(
        id = 0,
        name = TOPIC_1_NAME,
        description = TOPIC_DESC,
        followed = true
    ),
    Topic(
        id = 1,
        name = TOPIC_2_NAME,
        description = TOPIC_DESC
    ),
    Topic(
        id = 2,
        name = TOPIC_3_NAME,
        description = TOPIC_DESC
    )
)

private val numberOfUnfollowedTopics = testTopics.filter { !it.followed }.size