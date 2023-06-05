package com.example.news.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.news.MainActivity
import com.example.news.ReadNewsactivity
import com.example.news.adapter.OtherNewsAdapter
import com.example.news.database.NewsEntity
import com.example.news.databinding.FragmentEntertainmentBinding

class EntertainmentFragment : Fragment() {

    lateinit var binding: FragmentEntertainmentBinding
    lateinit var adapter: OtherNewsAdapter
    lateinit var arrListData:List<NewsEntity>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEntertainmentBinding.inflate(layoutInflater)

        arrListData = MainActivity.entertainmentNews
        adapter = OtherNewsAdapter(arrListData,this)
        binding.newsRecyclerView.adapter = adapter


        return binding.root

    }

        fun onItemClicked(url: String) {
            val intent = Intent(context, ReadNewsactivity::class.java)
            intent.putExtra("url",url)
            startActivity(intent)
        }


    }