package com.example.news.database

import android.content.Context
import androidx.lifecycle.*

class NewsViewModel:ViewModel() {

    private var newsLiveData: MutableLiveData<List<NewsEntity>>? = null

    //get news from API
    fun getNews(category: String?): MutableLiveData<List<NewsEntity>>? {

        newsLiveData = category.let {
            NewsRepository().getNewsApiCall(it)
        }

        return newsLiveData
    }

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkEmptyDatabase(db: List<NewsEntity>){
        emptyDatabase.value = db.isEmpty()
    }
    var newsData: LiveData<List<NewsEntity>>? = null

    fun insertNews(context: Context,news: NewsEntity){
        NewsRepository.insertNews(context,news)
    }

    fun deleteNews(news: NewsEntity) {
        NewsRepository.deleteNews(news)
    }

    fun getNewsFromDB(context: Context): LiveData<List<NewsEntity>>? {
        newsData = NewsRepository.getAllNews(context)
        return newsData
    }

}
