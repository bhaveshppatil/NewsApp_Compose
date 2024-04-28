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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moengage.newsapp.R
import com.moengage.newsapp.model.Article
import com.moengage.newsapp.model.Source
import com.moengage.newsapp.ui.theme.NewsAppTheme
import com.moengage.newsapp.ui.theme.primaryDark
import com.moengage.newsapp.utils.ContentDescription

@Composable
fun NewsScreen(
    navController: NavHostController,
    listOfArticles: List<Article>?,
    onOlderToNewerFilterApply: () -> Unit,
    onArticleClicked: (articleUrl: String) -> Unit
) {
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

@Preview
@Composable
private fun NewsPreviewScreen() {
    NewsAppTheme {
        NewsScreen(
            navController = rememberNavController(),
            listOfArticles =
            listOf(
                Article(
                    author = "Sarah Perez",
                    content = "Millennials’ interest in self-care has helped to fuel an entirely new market for mobile apps focused on health and wellness. Last year alone, the top 10 meditation apps pulled in \$195 million — a 52% increase from 2018, for example. A newcomer to the self-car… [+3829 chars]",
                    description = "Millennials’ interest in self-care has helped to fuel an entirely new market for mobile apps focused on health and wellness. Last year alone, the top 10 meditation apps pulled in \$195 million — a 52% increase from 2018, for example. A newcomer to the self-car…",
                    publishedAt = "2020-02-10T16:52:59Z",
                    source = Source(
                        id = "techcrunch",
                        name = "TechCrunch",
                    ),
                    title = "Is this what an early-stage slowdown looks like?",
                    url = "",
                    urlToImage = ""
                ),
                Article(
                    author = "Sarah Perez",
                    content = "Millennials’ interest in self-care has helped to fuel an entirely new market for mobile apps focused on health and wellness. Last year alone, the top 10 meditation apps pulled in \$195 million — a 52% increase from 2018, for example. A newcomer to the self-car… [+3829 chars]",
                    description = "Millennials’ interest in self-care has helped to fuel an entirely new market for mobile apps focused on health and wellness. Last year alone, the top 10 meditation apps pulled in \$195 million — a 52% increase from 2018, for example. A newcomer to the self-car…",
                    publishedAt = "2020-02-10T16:52:59Z",
                    source = Source(
                        id = "techcrunch",
                        name = "TechCrunch",
                    ),
                    title = "Is this what an early-stage slowdown looks like?",
                    url = "",
                    urlToImage = ""
                ),
                Article(
                    author = "Sarah Perez",
                    content = "Millennials’ interest in self-care has helped to fuel an entirely new market for mobile apps focused on health and wellness. Last year alone, the top 10 meditation apps pulled in \$195 million — a 52% increase from 2018, for example. A newcomer to the self-car… [+3829 chars]",
                    description = "Millennials’ interest in self-care has helped to fuel an entirely new market for mobile apps focused on health and wellness. Last year alone, the top 10 meditation apps pulled in \$195 million — a 52% increase from 2018, for example. A newcomer to the self-car…",
                    publishedAt = "2020-02-10T16:52:59Z",
                    source = Source(
                        id = "techcrunch",
                        name = "TechCrunch",
                    ),
                    title = "Is this what an early-stage slowdown looks like?",
                    url = "",
                    urlToImage = ""
                ),

                Article(
                    author = "Sarah Perez",
                    content = "Millennials’ interest in self-care has helped to fuel an entirely new market for mobile apps focused on health and wellness. Last year alone, the top 10 meditation apps pulled in \$195 million — a 52% increase from 2018, for example. A newcomer to the self-car… [+3829 chars]",
                    description = "Millennials’ interest in self-care has helped to fuel an entirely new market for mobile apps focused on health and wellness. Last year alone, the top 10 meditation apps pulled in \$195 million — a 52% increase from 2018, for example. A newcomer to the self-car…",
                    publishedAt = "2020-02-10T16:52:59Z",
                    source = Source(
                        id = "techcrunch",
                        name = "TechCrunch",
                    ),
                    title = "Is this what an early-stage slowdown looks like?",
                    url = "",
                    urlToImage = ""
                ),
                Article(
                    author = "Sarah Perez",
                    content = "Millennials’ interest in self-care has helped to fuel an entirely new market for mobile apps focused on health and wellness. Last year alone, the top 10 meditation apps pulled in \$195 million — a 52% increase from 2018, for example. A newcomer to the self-car… [+3829 chars]",
                    description = "Millennials’ interest in self-care has helped to fuel an entirely new market for mobile apps focused on health and wellness. Last year alone, the top 10 meditation apps pulled in \$195 million — a 52% increase from 2018, for example. A newcomer to the self-car…",
                    publishedAt = "2020-02-10T16:52:59Z",
                    source = Source(
                        id = "techcrunch",
                        name = "TechCrunch",
                    ),
                    title = "Is this what an early-stage slowdown looks like?",
                    url = "",
                    urlToImage = ""
                )
            ),
            {}, {}
        )
    }
}