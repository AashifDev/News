package com.example.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.ReadLaterActivity
import com.example.news.database.NewsEntity
import com.squareup.picasso.Picasso

class ReadLaterAdapter(val news: List<NewsEntity>, val listener: ReadLaterActivity):
RecyclerView.Adapter<ReadLaterAdapter.ViewHolder>() {


    class ViewHolder(view: View):
        RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.title)
        val img: ImageView = view.findViewById(R.id.img)
        val des: TextView = view.findViewById(R.id.des)
        val del:ImageView = view.findViewById(R.id.delete)
        val view:ConstraintLayout = view.findViewById(R.id.view)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.readlater_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = news[position]
        holder.title.text = item.headLine

        if (!item.image.isNullOrEmpty()){
            Picasso.get().load(item.image).into(holder.img)
        }
        holder.des.text = item.description

        holder.del.setOnClickListener {
          listener.onClick(item)
        }

        holder.view.setOnClickListener {
            item.url.let {
                listener.onItemClicked(it)
            }
        }
    }

    override fun getItemCount() = news.size

}