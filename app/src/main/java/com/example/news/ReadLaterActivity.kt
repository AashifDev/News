package com.example.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.news.adapter.ReadLaterAdapter
import com.example.news.database.NewsEntity
import com.example.news.database.NewsViewModel
import com.example.news.databinding.ActivityReadLaterBinding

class ReadLaterActivity : AppCompatActivity() {
    lateinit var binding: ActivityReadLaterBinding
    lateinit var adapter: ReadLaterAdapter
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsData: MutableList<NewsEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadLaterBinding.inflate(layoutInflater)

        newsData = arrayListOf()

        adapter = ReadLaterAdapter(newsData,this)

        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        binding.back.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        binding.noData.visibility = View.VISIBLE

        viewModel.emptyDatabase.observe(this, Observer {
            showEmptyDatabase(it)
        })

        // Get Saved News
        viewModel.getNewsFromDB(context = applicationContext)?.observe(this) {
            viewModel.checkEmptyDatabase(it)
            newsData.clear()
            newsData.addAll(it)
            adapter.notifyDataSetChanged()
        }


        binding.newsRecyclerView.adapter = adapter
        setContentView(binding.root)
    }

    private fun showEmptyDatabase(db: Boolean) {
        if (db){
            binding.noData.visibility = View.VISIBLE
        }else{
            binding.noData.visibility = View.GONE
        }
    }

    fun onClick(item: NewsEntity) {
        viewModel.deleteNews(item)

    }

    fun onItemClicked(url: String?) {
        val intent = Intent(this, ReadNewsactivity::class.java)
        intent.putExtra("url",url)
        startActivity(intent)
    }
}