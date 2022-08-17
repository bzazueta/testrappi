package com.example.myrappi.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrappi.R
import com.example.myrappi.data.model.UpComingModel
import com.example.myrappi.databinding.AdapterMovieBinding


class RecyclerAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var movies = mutableListOf<String>()
    fun setMovieList(movies: List<String>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]
      //  holder.binding.name.text = movie.name
        Glide.with(holder.itemView.context).load(movie).into(holder.binding.imageview)
    }
    override fun getItemCount(): Int {
        return movies.size
    }
}
class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {
}