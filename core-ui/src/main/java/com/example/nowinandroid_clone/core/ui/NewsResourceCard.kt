package com.example.nowinandroid_clone.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import coil.compose.AsyncImage
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
    authors: List<Author>
) {
    if (authors.isNotEmpty()) {
        val author = authors[0]
        val authorNameFormatted =
            author.name.uppercase(ConfigurationCompat.getLocales(LocalConfiguration.current).get(0))

        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(24.dp),
                contentScale = ContentScale.Crop,
                model = author.imageUrl,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(authorNameFormatted, style = MaterialTheme.typography.body2)
        }
    }
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
            NewsResourceAuthors(newsResource.authors)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row {
            NewsResourceTitle(
                newsResource.title,
                modifier = Modifier.fillMaxWidth(.8f)
            )
            Spacer(modifier = Modifier.weight(1f))
            BookmarkButton(isBookmarked, onToggleBookmark)
        }
        Spacer(modifier = Modifier.height(12.dp))
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
            imageUrl = "https://source.unsplash.com/Yc5sL-ejk6U"
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