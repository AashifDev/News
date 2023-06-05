package com.example.news.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper{
    private const val url = "https://newsapi.org/v2/"
    fun getInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}