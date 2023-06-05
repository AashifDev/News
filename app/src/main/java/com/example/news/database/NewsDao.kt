package com.example.news.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: NewsEntity)

    @Query("SELECT * FROM News_Table")
    fun getNewsFromDatabase(): LiveData<List<NewsEntity>>

    @Delete
    fun deleteNews(news: NewsEntity)

}
