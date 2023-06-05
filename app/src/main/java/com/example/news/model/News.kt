package com.example.news.model

data class News(
    val articles: ArrayList<Article>,
    val status: String,
    val totalResults: Int
)