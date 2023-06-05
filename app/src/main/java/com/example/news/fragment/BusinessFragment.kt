package com.example.news.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.news.MainActivity
import com.example.news.ReadNewsactivity
import com.example.news.adapter.NewsAdapter
import com.example.news.database.NewsEntity
import com.example.news.database.NewsViewModel
import com.example.news.databinding.FragmentBusinessBinding

class BusinessFragment : Fragment() {

    lateinit var binding: FragmentBusinessBinding
    lateinit var adapter: NewsAdapter
    lateinit var arrListData:List<NewsEntity>
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBusinessBinding.inflate(layoutInflater)

        arrListData = MainActivity.businessNews
        adapter = NewsAdapter(arrListData,this)
        binding.newsRecyclerView.adapter = adapter
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]



        return binding.root
    }

    fun onItemClicked(url: String) {
        val intent = Intent(context, ReadNewsactivity::class.java)
        intent.putExtra("url",url)
        startActivity(intent)
    }

    fun SaveLater(item: NewsEntity) {

        viewModel.insertNews(requireContext(),item)
        Toast.makeText(context, "Saved Successfully", Toast.LENGTH_SHORT).show()

    }


}
