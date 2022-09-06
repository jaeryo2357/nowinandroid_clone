package com.example.nowinandroid_clone.ui

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.nowinandroid_clone.data.news.NewsResource
import com.example.nowinandroid_clone.ui.theme.NiaTheme

@Composable
fun ResourceAuthors(
    newsResource: NewsResource
) {
    TODO()
}

@Composable
fun ResourceHeaderImage(
    newsResource: NewsResource
) {
    TODO()
}

@Composable
fun ResourceTitle(
    newsResource: NewsResource
) {
    TODO()
}

@Composable
fun ResourceDate(
    newsResource: NewsResource
) {
    TODO()
}

@Composable
fun ResourceLink(
    newsResource: NewsResource
) {
    TODO()
}

@Composable
fun ResourceShortDescription(
    newsResource: NewsResource
) {
    TODO()
}

@Composable
fun ResourceTopics(
    newsResource: NewsResource
) {
    TODO()
}

@Composable
fun ResourceCardExpanded() {
    TODO()
}

@Preview("Expanded resource card")
@Preview("Expanded resource card (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ExpandedResourcePreview() {
    NiaTheme {
        Surface {
            ResourceCardExpanded()
        }
    }
}