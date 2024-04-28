package com.moengage.newsapp.ui.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.moengage.newsapp.R
import com.moengage.newsapp.model.Article
import com.moengage.newsapp.model.Source
import com.moengage.newsapp.ui.theme.NewsAppTheme
import com.moengage.newsapp.ui.theme.primaryLightGreen
import com.moengage.newsapp.utils.ContentDescription

@Composable
fun NewsItemView(
    article: Article,
    onArticleClick: (article: Article) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            .border(width = Dp.Unspecified, color = Color.White, shape = RoundedCornerShape(20.dp))
            .padding(10.dp)
            .clickable { onArticleClick.invoke(article) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = article.urlToImage,
            contentDescription = ContentDescription.NewsImage,
            modifier = Modifier
                .size(100.dp)
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.size(15.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = article.source.name,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Gray
                )
            )
            Spacer(modifier = Modifier.size(5.dp))

            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )

            Text(
                text = article.description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsItemPreview() {
    NewsAppTheme {
        NewsItemView(
            article = Article(
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
            {}
        )
    }
}