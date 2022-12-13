package com.example.nowinandroid_clone.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nowinandroid_clone.core.model.data.Author
import com.example.nowinandroid_clone.core.model.data.NewsResource
import com.example.nowinandroid_clone.core.model.data.NewsResourceType
import com.example.nowinandroid_clone.core.model.data.Topic
import com.example.nowinandroid_clone.core.ui.theme.NiaTheme
import kotlinx.datetime.Instant

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
                newsResource.title,
                modifier = Modifier.fillMaxWidth(.8f)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        NewsResourceShortDescription(newsResource.content)
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

@Preview("NewsResourceCardExpanded")
@Composable
fun ExpandedNewsResourcePreview() {
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

private val newsResource = NewsResource(
    id = 1,
    episodeId = 1,
    title = "Title",
    content = "Content",
    url = "url",
    publishDate = Instant.DISTANT_FUTURE,
    type = NewsResourceType.Article,
    authors = listOf(
        Author(
            id = 1,
            name = "Name",
            imageUrl = "imageUrl"
        )
    ),
    topics = listOf(
        Topic(
            id = 1,
            name = "Name",
            description = "Description"
        )
    )
)