package com.example.news.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.api.ApiInterface
import com.example.news.api.RetrofitHelper
import com.example.news.model.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsRepository {

    companion object {

        var apiRequestError = false
        var errorMessage = "error"

        private var newsDatabase: NewsDatabase? = null

        private fun initializeDB(context: Context): NewsDatabase {
            return NewsDatabase.getDatabaseClient(context)
        }

        fun insertNews(context: Context, news: NewsEntity) {

            newsDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                newsDatabase!!.newsDao().insertNews(news)
            }
        }

        fun deleteNews(news: NewsEntity) {
            CoroutineScope(Dispatchers.IO).launch {
                newsDatabase!!.newsDao().deleteNews(news)
            }

        }

        fun getAllNews(context: Context): LiveData<List<NewsEntity>> {

            newsDatabase = initializeDB(context)
            return newsDatabase!!.newsDao().getNewsFromDatabase()
        }

    }

    // get news from API
    fun getNewsApiCall(category: String?): MutableLiveData<List<NewsEntity>> {

        val newsList = MutableLiveData<List<NewsEntity>>()

        val call = RetrofitHelper.getInstance().create(ApiInterface::class.java)
            .getNews("in", category, "f38bb37fc5f0451c993b61898a0edcff") //put your api key here

        call.enqueue(object : Callback<News> {
            override fun onResponse(
                call: Call<News>, response: Response<News>) {

                if (response.isSuccessful) {

                    val body = response.body()

                    if (body != null) {
                        val tempNewsList = mutableListOf<NewsEntity>()

                        body.articles.forEach {
                            tempNewsList.add(
                                NewsEntity(
                                    it.title,
                                    it.urlToImage,
                                    it.description,
                                    it.url,
                                )
                            )
                        }
                        newsList.value = tempNewsList

                    }

                }
                Log.d("tag", "{${newsList.value}")
            }


            override fun onFailure(call: Call<News>, t: Throwable) {

                apiRequestError = true
                errorMessage = t.localizedMessage as String
                Log.d("err_msg", "msg" + t.localizedMessage)
            }
        })

        return newsList
    }

}
