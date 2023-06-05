package com.example.news.api

import com.example.news.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    //https://newsapi.org/v2/top-headlines?country=us&apiKey=f38bb37fc5f0451c993b61898a0edcff

    @GET("/v2/top-headlines")
    fun getNews(@Query("country") country : String, @Query("category") category : String?, @Query("apiKey") key : String) :
            Call<News>

}