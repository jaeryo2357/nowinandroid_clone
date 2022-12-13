package com.example.nowinandroid_clone.feature.foryou

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.nowinandroid_clone.core.model.data.FollowableTopic
import com.example.nowinandroid_clone.core.model.data.NewsResource
import com.example.nowinandroid_clone.core.model.data.NewsResourceType
import com.example.nowinandroid_clone.core.model.data.SaveableNewsResource
import com.example.nowinandroid_clone.core.model.data.Topic
import com.example.nowinandroid_clone.core.testing.repository.TestNewsRepository
import com.example.nowinandroid_clone.core.testing.repository.TestTopicsRepository
import com.example.nowinandroid_clone.core.testing.util.TestDispatcherRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ForYouViewModelTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val topicsRepository =
        TestTopicsRepository()
    private val newsRepository =
        TestNewsRepository()
    private lateinit var viewModel: ForYouViewModel

    @Before
    fun setup() {
        viewModel = ForYouViewModel(
            topicsRepository = topicsRepository,
            newsRepository = newsRepository,
            savedStateHandle = SavedStateHandle()
        )
    }

    @Test
    fun stateIsInitiallyLoading() = runTest {
        viewModel.uiState.test {
            assertEquals(ForYouFeedUiState.Loading, awaitItem())
            cancel()
        }
    }

    @Test
    fun stateIsLoadingWhenFollowedTopicsAreLoading() = runTest {
        viewModel.uiState.test {
            assertEquals(ForYouFeedUiState.Loading, awaitItem())
            topicsRepository.sendTopics(sampleTopics)

            cancel()
        }
    }

    @Test
    fun stateIsLoadingWhenNewsResourcesAreLoading() = runTest {
        viewModel.uiState.test {
            awaitItem()
            topicsRepository.sendTopics(sampleTopics)
            topicsRepository.setFollowedTopicIds(emptySet())

            cancel()
        }
    }

    @Test
    fun stateIsTopicSelectionAfterLoadingEmptyFollowedTopics() = runTest {
        viewModel.uiState
            .test {
                awaitItem()
                topicsRepository.sendTopics(sampleTopics)
                topicsRepository.setFollowedTopicIds(emptySet())
                newsRepository.sendNewsResources(sampleNewsResources)

                assertEquals(
                    ForYouFeedUiState.PopulatedFeed.FeedWithTopicSelection(
                        topics = listOf(
                            FollowableTopic(
                                topic = Topic(
                                    id = 0,
                                    name = "Headlines",
                                    description = ""
                                ),
                                isFollowed = false
                            ),
                            FollowableTopic(
                                topic = Topic(
                                    id = 1,
                                    name = "UI",
                                    description = ""
                                ),
                                isFollowed = false
                            ),
                            FollowableTopic(
                                topic = Topic(
                                    id = 2,
                                    name = "Tools",
                                    description = "",
                                ),
                                isFollowed = false
                            ),
                        ),
                        feed = emptyList()
                    ),
                    awaitItem()
                )
                cancel()
            }
    }

    @Test
    fun stateIsWithoutTopicSelectionAfterLoadingFollowedTopics() = runTest {
        viewModel.uiState
            .test {
                awaitItem()
                topicsRepository.sendTopics(sampleTopics)
                topicsRepository.setFollowedTopicIds(setOf(0, 1))
                newsRepository.sendNewsResources(sampleNewsResources)

                assertEquals(
                    ForYouFeedUiState.PopulatedFeed.FeedWithoutTopicSelection(
                        feed = sampleNewsResources.map {
                            SaveableNewsResource(
                                newsResource = it,
                                isSaved = false
                            )
                        }
                    ),
                    awaitItem()
                )
                cancel()
            }
    }

    @Test
    fun topicSelectionUpdatesAfterSelectingTopic() = runTest {
        viewModel.uiState
            .test {
                awaitItem()
                topicsRepository.sendTopics(sampleTopics)
                topicsRepository.setFollowedTopicIds(emptySet())
                newsRepository.sendNewsResources(sampleNewsResources)

                awaitItem()
                viewModel.updateTopicSelection(1, isChecked = true)

                assertEquals(
                    ForYouFeedUiState.PopulatedFeed.FeedWithTopicSelection(
                        topics = listOf(
                            FollowableTopic(
                                topic = Topic(
                                    id = 0,
                                    name = "Headlines",
                                    description = ""
                                ),
                                isFollowed = false
                            ),
                            FollowableTopic(
                                topic = Topic(
                                    id = 1,
                                    name = "UI",
                                    description = ""
                                ),
                                isFollowed = true
                            ),
                            FollowableTopic(
                                topic = Topic(
                                    id = 2,
                                    name = "Tools",
                                    description = "",
                                ),
                                isFollowed = false
                            )
                        ),
                        feed = listOf(
                            SaveableNewsResource(
                                newsResource = sampleNewsResources[1],
                                isSaved = false
                            ),
                            SaveableNewsResource(
                                newsResource = sampleNewsResources[2],
                                isSaved = false
                            )
                        )
                    ),
                    awaitItem()
                )
                cancel()
            }
    }

    @Test
    fun topicSelectionUpdatesAfterUnselectingTopic() = runTest {
        viewModel.uiState
            .test {
                awaitItem()
                topicsRepository.sendTopics(sampleTopics)
                topicsRepository.setFollowedTopicIds(emptySet())
                newsRepository.sendNewsResources(sampleNewsResources)

                awaitItem()
                viewModel.updateTopicSelection(1, isChecked = true)

                awaitItem()
                viewModel.updateTopicSelection(1, isChecked = false)

                assertEquals(
                    ForYouFeedUiState.PopulatedFeed.FeedWithTopicSelection(
                        topics = listOf(
                            FollowableTopic(
                                topic = Topic(
                                    id = 0,
                                    name = "Headlines",
                                    description = ""
                                ),
                                isFollowed = false
                            ),
                            FollowableTopic(
                                topic = Topic(
                                    id = 1,
                                    name = "UI",
                                    description = ""
                                ),
                                isFollowed = false
                            ),
                            FollowableTopic(
                                topic = Topic(
                                    id = 2,
                                    name = "Tools",
                                    description = "",
                                ),
                                isFollowed = false
                            )
                        ),
                        feed = emptyList()
                    ),
                    awaitItem()
                )
                cancel()
            }
    }

    @Test
    fun topicSelectionUpdatesAfterSavingTopics() = runTest {
        viewModel.uiState
            .test {
                awaitItem()
                topicsRepository.sendTopics(sampleTopics)
                topicsRepository.setFollowedTopicIds(emptySet())
                newsRepository.sendNewsResources(sampleNewsResources)

                awaitItem()
                viewModel.updateTopicSelection(1, isChecked = true)

                awaitItem()
                viewModel.saveFollowedTopics()

                assertEquals(
                    ForYouFeedUiState.PopulatedFeed.FeedWithoutTopicSelection(
                        feed = listOf(
                            SaveableNewsResource(
                                newsResource = sampleNewsResources[1],
                                isSaved = false
                            ),
                            SaveableNewsResource(
                                newsResource = sampleNewsResources[2],
                                isSaved = false
                            )
                        )
                    ),
                    awaitItem()
                )
                assertEquals(setOf(1), topicsRepository.getCurrentFollowedTopics())
                cancel()
            }
    }

    @Test
    fun newsResourceSelectionUpdatesAfterLoadingFollowedTopics() = runTest {
        viewModel.uiState
            .test {
                awaitItem()
                topicsRepository.sendTopics(sampleTopics)
                topicsRepository.setFollowedTopicIds(setOf(1))
                newsRepository.sendNewsResources(sampleNewsResources)
                viewModel.updateNewsResourceSaved(2, true)

                assertEquals(
                    ForYouFeedUiState.PopulatedFeed.FeedWithoutTopicSelection(
                        feed = listOf(
                            SaveableNewsResource(
                                newsResource = sampleNewsResources[1],
                                isSaved = true
                            ),
                            SaveableNewsResource(
                                newsResource = sampleNewsResources[2],
                                isSaved = false
                            )
                        )
                    ),
                    awaitItem()
                )
                cancel()
            }
    }
}

private val sampleTopics = listOf(
    Topic(
        id = 0,
        name = "Headlines",
        description = ""
    ),
    Topic(
        id = 1,
        name = "UI",
        description = ""
    ),
    Topic(
        id = 2,
        name = "Tools",
        description = ""
    )
)

private val sampleNewsResources = listOf(
    NewsResource(
        id = 1,
        episodeId = 52,
        title = "Thanks for helping us reach 1M YouTube Subscribers",
        content = "Thank you everyone for following the Now in Android series and everything the " +
                "Android Developers YouTube channel has to offer. During the Android Developer " +
                "Summit, our YouTube channel reached 1 million subscribers! Here’s a small video to " +
                "thank you all.",
        url = "https://youtu.be/-fJ6poHQrjM",
        publishDate = Instant.parse("2021-11-09T00:00:00.000Z"),
        type = NewsResourceType.Video,
        topics = listOf(
            Topic(
                id = 0,
                name = "Headlines",
                description = ""
            )
        ),
        authors = emptyList()
    ),
    NewsResource(
        id = 2,
        episodeId = 52,
        title = "Transformations and customisations in the Paging Library",
        content = "A demonstration of different operations that can be performed with Paging. " +
                "Transformations like inserting separators, when to create a new pager, and " +
                "customisation options for consuming PagingData.",
        url = "https://youtu.be/ZARz0pjm5YM",
        publishDate = Instant.parse("2021-11-01T00:00:00.000Z"),
        type = NewsResourceType.Video,
        topics = listOf(
            Topic(
                id = 1,
                name = "UI",
                description = ""
            ),
        ),
        authors = emptyList()
    ),
    NewsResource(
        id = 3,
        episodeId = 52,
        title = "Community tip on Paging",
        content = "Tips for using the Paging library from the developer community",
        url = "https://youtu.be/r5JgIyS3t3s",
        publishDate = Instant.parse("2021-11-08T00:00:00.000Z"),
        type = NewsResourceType.Video,
        topics = listOf(
            Topic(
                id = 1,
                name = "UI",
                description = ""
            ),
        ),
        authors = emptyList()
    ),
)