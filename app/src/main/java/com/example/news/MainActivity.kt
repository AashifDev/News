package com.example.news

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.example.news.Constant.BUSINESS
import com.example.news.Constant.ENTERTAINMENT
import com.example.news.Constant.SPORTS
import com.example.news.Constant.TECHNOLOGY
import com.example.news.Constant.TOTAL_NEWS_TAB
import com.example.news.adapter.TabLayoutAdapter
import com.example.news.database.NewsEntity
import com.example.news.database.NewsViewModel
import com.example.news.databinding.ActivityMainBinding
import com.example.news.model.News
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var tabLayoutAdapter: TabLayoutAdapter
    private lateinit var viewModel: NewsViewModel
    private var totalRequestCount = 0

    private val newsCategory = arrayOf(
        BUSINESS,
        ENTERTAINMENT,
        TECHNOLOGY,
        SPORTS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]


        if (ConnectionManager().checkConnection(this)){
            binding.noData.visibility = View.GONE
            binding.reload.visibility = View.GONE
            binding.progressCircular.visibility = View.VISIBLE

        }else{
            binding.noData.visibility = View.VISIBLE
            binding.reload.visibility = View.VISIBLE
            binding.progressCircular.visibility = View.GONE

        }

        requestNews(BUSINESS, businessNews)
        requestNews(ENTERTAINMENT, entertainmentNews)
        requestNews(TECHNOLOGY,techNews)
        requestNews(SPORTS, sportsNews)

        binding.toolbar.bookmark.setOnClickListener {
            startActivity(Intent(this,ReadLaterActivity::class.java))
        }

        tabLayoutAdapter = TabLayoutAdapter(supportFragmentManager, lifecycle)
        binding.pager.adapter = tabLayoutAdapter
        binding.pager.visibility = View.GONE
    }

    private fun requestNews(newsCategory: String, newsData: MutableList<NewsEntity>) {
        viewModel.getNews(category = newsCategory)?.observe(this) {
            newsData.addAll(it)
            totalRequestCount += 1

            // If main fragment loaded then attach the fragment to viewPager
            if (newsCategory == BUSINESS) {
                binding.progressCircular.visibility = View.GONE
                setViewPager()
            }

            if (totalRequestCount == TOTAL_NEWS_TAB) {
                binding.pager.offscreenPageLimit = 5
            }
            Log.d("tag","{$newsData}")
        }
    }

    private fun setViewPager() {
        if (!apiRequestError) {
            binding.pager.visibility = View.VISIBLE
            TabLayoutMediator(binding.tabs, binding.pager) { tab,pos->
                tab.text = newsCategory[pos]
            }.attach()

        } else {
            binding.noData.visibility = View.VISIBLE
            binding.reload.visibility = View.VISIBLE
        }
    }

    companion object {
        var entertainmentNews: MutableList<NewsEntity> = mutableListOf()
        var businessNews: MutableList<NewsEntity> = mutableListOf()
        var sportsNews: MutableList<NewsEntity> = mutableListOf()
        var techNews: MutableList<NewsEntity> = mutableListOf()
        var apiRequestError = false
    }


}


