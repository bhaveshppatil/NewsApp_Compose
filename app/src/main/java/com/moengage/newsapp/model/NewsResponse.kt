package com.moengage.newsapp.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerialName("articles")
    val articles: List<Article>,
    @SerialName("status")
    val status: String
)

@Serializable
data class Article(
    @SerialName("author")
    val author: String,
    @SerialName("content")
    val content: String,
    @SerialName("description")
    val description: String,
    @SerialName("publishedAt")
    val publishedAt: String,
    @SerialName("source")
    val source: Source,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String,
    @SerialName("urlToImage")
    val urlToImage: String
)

@Serializable
data class Source(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String
)