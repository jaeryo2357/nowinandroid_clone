package com.example.nowinandroid_clone.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.nowinandroid_clone.R
import com.example.nowinandroid_clone.data.fake.FakeDataSource
import com.example.nowinandroid_clone.data.model.NewsResource
import com.example.nowinandroid_clone.data.network.NetworkAuthor
import com.example.nowinandroid_clone.data.network.NetworkEpisode
import com.example.nowinandroid_clone.data.network.NetworkTopic
import com.example.nowinandroid_clone.data.network.asEntity
import com.example.nowinandroid_clone.ui.theme.NiaTheme

@Composable
fun BookmarkButton(
    isBookmarked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val clickActionLabel = stringResource(
        if (isBookmarked) R.string.unbookmark else R.string.bookmark
    )
    IconToggleButton(
        checked = isBookmarked,
        onCheckedChange = { onClick() },
        modifier = modifier.semantics {
            this.onClick(label = clickActionLabel, action = null)
        }
    ) {
        Icon(
            imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
            contentDescription = null
        )
    }
}

@Composable
fun NewsResourceAuthors(
    newsResource: NewsResource
) {
    TODO()
}

@Composable
fun NewsResourceCardExpanded(
    newsResource: NewsResource,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row {
            NewsResourceTitle(
                newsResource.entity.title,
                modifier = Modifier.fillMaxWidth(.8f)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        NewsResourceShortDescription(newsResource.entity.content)
    }
}

@Composable
fun NewsResourceHeaderImage(
    newsResource: NewsResource
) {
    TODO()
}

@Composable
fun NewsResourceTitle(
    newsResourceTitle: String,
    modifier: Modifier = Modifier
) {
    Text(
        newsResourceTitle,
        style = MaterialTheme.typography.h4,
        modifier = modifier
    )
}

@Composable
fun NewsResourceDate(
    newsResource: NewsResource
) {
    TODO()
}

@Composable
fun NewsResourceLink(
    newsResource: NewsResource
) {
    TODO()
}

@Composable
fun NewsResourceShortDescription(
    newsResourceShortDescription: String
) {
    Text(newsResourceShortDescription, style = MaterialTheme.typography.body1)
}

@Composable
fun NewsResourceTopics(
    newsResource: NewsResource
) {
    TODO()
}

@Composable
fun NewsResourceCardExpanded() {
    TODO()
}

@Preview
@Composable
fun ExpandedNewsResourcePreview(
    @PreviewParameter(NewsResourcePreviewParameterProvider::class)
    newsResource: NewsResource
) {
    NiaTheme {
        Surface {
            NewsResourceCardExpanded(newsResource, true, {})
        }
    }
}

@Preview("Bookmark Button")
@Composable
fun BookmarkButtonPreview() {
    NiaTheme {
        Surface {
            BookmarkButton(isBookmarked = false, onClick = { })
        }
    }
}

@Preview("Bookmark Button Bookmarked")
@Composable
fun BookmarkButtonBookmarkedPreview() {
    NiaTheme {
        Surface {
            BookmarkButton(isBookmarked = true, onClick = { })
        }
    }
}

class NewsResourcePreviewParameterProvider : PreviewParameterProvider<NewsResource> {
    override val values = sequenceOf(
        NewsResource(
            FakeDataSource.sampleResource.asEntity(),
            NetworkEpisode(
                id = 1,
                name = "Now in Android 40",
                alternateVideo = null,
                alternateAudio = null,
                publishDate = FakeDataSource.sampleResource.publishDate
            ).asEntity(),
            listOf(
                NetworkAuthor(
                    id = 1,
                    name = "Android",
                    imageUrl = ""
                )
                    .asEntity()
            ),
            listOf(
                NetworkTopic(
                    id = 3,
                    name = "Performance",
                    description = ""
                ).asEntity()
            )
        )
    )
}