package com.example.news.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NEWS_TABLE")
data class NewsEntity(


    @PrimaryKey(autoGenerate = false)

    @ColumnInfo(name = "headline")
    val headLine: String,

    @ColumnInfo(name = "imgurl")
    val image: String?,


    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "url")
    val url: String?,

    @ColumnInfo(name = "isLike")
    val isLike: Boolean = false


)

