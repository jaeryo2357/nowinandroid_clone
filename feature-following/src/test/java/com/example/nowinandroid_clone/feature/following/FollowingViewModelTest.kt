package com.example.nowinandroid_clone.feature.following

import app.cash.turbine.test
import com.example.nowinandroid_clone.core.model.data.FollowableTopic
import com.example.nowinandroid_clone.core.testing.repository.TestTopicsRepository
import com.example.nowinandroid_clone.core.model.data.Topic
import com.example.nowinandroid_clone.core.testing.util.TestDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FollowingViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val topicsRepository =
        TestTopicsRepository()
    private lateinit var viewModel: FollowingViewModel

    @Before
    fun setup() {
        viewModel = FollowingViewModel(
            topicsRepository =
            topicsRepository
        )
    }

    @Test
    fun uiState_whenInitialized_thenShowLoading() = runTest {
        viewModel.uiState.test {
            assertEquals(FollowingUiState.Loading, awaitItem())
            cancel()
        }
    }

    @Test
    fun uiState_whenFollowedTopicsAreLoading_thenShowLoading() = runTest {
        viewModel.uiState.test {
            assertEquals(FollowingUiState.Loading, awaitItem())
            topicsRepository.setFollowedTopicIds(emptySet())
            cancel()
        }
    }

    @Test
    fun uiState_whenFollowingNewTopic_thenShowUpdateTopics() = runTest {
        viewModel.uiState.test {
            awaitItem()
            topicsRepository.sendTopics(testInputTopics.map { it.topic })
            topicsRepository.setFollowedTopicIds(setOf(testInputTopics[0].topic.id))
            awaitItem()
            viewModel.followTopic(
                followedTopicId = testInputTopics[1].topic.id,
                followed = !testInputTopics[1].isFollowed
            )

            assertEquals(
                FollowingUiState.Topics(topics = testOutputTopics),
                awaitItem()
            )
            cancel()
        }
    }

    @Test
    fun uiState_whenUnFollowingNewTopic_thenShowUpdateTopics() = runTest {
        viewModel.uiState.test {
            awaitItem()
            topicsRepository.sendTopics(testOutputTopics.map { it.topic })
            topicsRepository.setFollowedTopicIds(setOf(testOutputTopics[0].topic.id, testOutputTopics[1].topic.id))
            awaitItem()
            viewModel.followTopic(
                followedTopicId = testOutputTopics[1].topic.id,
                followed = false
            )

            assertEquals(
                FollowingUiState.Topics(topics = testInputTopics),
                awaitItem()
            )
            cancel()
        }
    }
}

private const val TOPIC_1_NAME = "Android Studio"
private const val TOPIC_2_NAME = "Build"
private const val TOPIC_3_NAME = "Compose"
private const val TOPIC_DESC = "At vero eos et accusamus et iusto odio dignissimos ducimus qui."

private val testInputTopics = listOf(
    FollowableTopic(
        Topic(
            id = 0,
            name = TOPIC_1_NAME,
            description = TOPIC_DESC
        ),
        isFollowed = true
    ),
    FollowableTopic(
        Topic(
            id = 0,
            name = TOPIC_1_NAME,
            description = TOPIC_DESC
        ),
        isFollowed = false
    ),
    FollowableTopic(
        Topic(
            id = 1,
            name = TOPIC_2_NAME,
            description = TOPIC_DESC
        ),
        isFollowed = false
    ),
    FollowableTopic(
        Topic(
            id = 2,
            name = TOPIC_3_NAME,
            description = TOPIC_DESC
        ),
        isFollowed = false
    )

)

private val testOutputTopics = listOf(
    FollowableTopic(
        Topic(
            id = 0,
            name = TOPIC_1_NAME,
            description = TOPIC_DESC
        ),
        isFollowed = true
    ),
    FollowableTopic(
        Topic(
            id = 1,
            name = TOPIC_2_NAME,
            description = TOPIC_DESC
        ),
        isFollowed = true
    ),
    FollowableTopic(
        Topic(
            id = 2,
            name = TOPIC_3_NAME,
            description = TOPIC_DESC
        ),
        isFollowed = false
    )
)