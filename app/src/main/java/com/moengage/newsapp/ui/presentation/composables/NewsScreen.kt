package com.moengage.newsapp.ui.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moengage.newsapp.R
import com.moengage.newsapp.model.Article
import com.moengage.newsapp.ui.theme.NewsAppTheme
import com.moengage.newsapp.ui.theme.primaryDark
import com.moengage.newsapp.utils.ContentDescription

@Composable
fun NewsScreen(
    listOfArticles: List<Article>?,
    onOlderToNewerFilterApply: () -> Unit,
    onArticleClicked: (articleUrl: String) -> Unit,
    isLoading: Boolean
) {
    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(100.dp)
                        .background(
                            color = primaryDark,
                            shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.moengage_logo_dark_2),
                        contentDescription = ContentDescription.NewsImage,
                    )
                }
            }

            item {
                NewsFilterView {
                    when (it) {
                        "Older to newer" -> {
                            onOlderToNewerFilterApply.invoke()
                        }
                    }
                }
            }

            itemsIndexed(listOfArticles ?: emptyList()) { _, article ->
                NewsItemView(article = article) {
                    onArticleClicked.invoke(it.url)
                }
            }
        }
    }
}

@Preview
@Composable
private fun NewsPreviewScreen() {
    NewsAppTheme {
        NewsScreen(
            listOfArticles = emptyList(),
            onArticleClicked = {},
            onOlderToNewerFilterApply = {},
            isLoading = true
        )
    }
}