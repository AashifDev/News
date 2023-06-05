package com.example.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.database.NewsEntity
import com.example.news.fragment.BusinessFragment
import com.example.news.fragment.EntertainmentFragment
import com.example.news.fragment.SportFragment
import com.example.news.fragment.TechnologyFragment
import com.squareup.picasso.Picasso

class NewsAdapter(val news: List<NewsEntity>, val fragment: Fragment):
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    //,val businessNews: BusinessFragment, val sportNews: SportFragment
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val title:TextView = view.findViewById(R.id.title)
        val img:ImageView = view.findViewById(R.id.img)
        val fav:TextView = view.findViewById(R.id.fav)
        val des:TextView = view.findViewById(R.id.des)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = news[position]
        holder.title.text = item.headLine
        Picasso.get().load(item.image).into(holder.img)

        if (!item.image.isNullOrEmpty()){
            holder.img.setImageResource(R.drawable.samplenews)
        }
        holder.des.text = item.description

        if (fragment is BusinessFragment){

            holder.fav.setOnClickListener {
                fragment.SaveLater(item)
            }
            holder.title.setOnClickListener {
                item.url?.let { it1 -> fragment.onItemClicked(it1) }
            }
            holder.img.setOnClickListener {
                item.url?.let { it1 -> fragment.onItemClicked(it1) }
            }
        }

        if (fragment is EntertainmentFragment){
            holder.title.setOnClickListener {
                item.url?.let { it1 -> fragment.onItemClicked(it1) }
            }
            holder.img.setOnClickListener {
                item.url?.let { it1 -> fragment.onItemClicked(it1) }
            }
        }

        if (fragment is TechnologyFragment){
            holder.title.setOnClickListener {
                item.url?.let { it1 -> fragment.onItemClicked(it1) }
            }
            holder.img.setOnClickListener {
                item.url?.let { it1 -> fragment.onItemClicked(it1) }
            }
        }

        if (fragment is SportFragment){
            holder.title.setOnClickListener {
                item.url?.let { it1 -> fragment.onItemClicked(it1) }
            }
            holder.img.setOnClickListener {
                item.url?.let { it1 -> fragment.onItemClicked(it1) }
            }
        }
    }

    override fun getItemCount() = news.size
}


