package com.example.news.model

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val id:Int,
    val url: String,
    val source: Source,
    val urlToImage: String
)