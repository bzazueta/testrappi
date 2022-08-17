package com.example.myrappi.ui.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrappi.R
import com.example.myrappi.data.model.FilerMovieModel
import com.example.myrappi.data.model.TopRatedModel
import com.example.myrappi.ui.view.DetailActivity

class FilterAdapter : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {
    var movies = mutableListOf<FilerMovieModel>()//MutableList<String>  = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(movies_ : ArrayList<FilerMovieModel>, context: Context){
        this.movies = movies_
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // for(i in superheros.results.indices)
        //{
        val image = "https://image.tmdb.org/t/p/w1280/"+  movies.get(position).image
        val title =  movies.get(position).title
        val original_language =   movies.get(position).original_language
        val release_date =  movies.get(position).release_date
        val vote_average  =  movies.get(position).vote_average
        val overview  = movies.get(position).overview
        val id  =  movies.get(position).id

        Glide.with(context).load(image).into(holder.imageview)
        holder.imageview.setOnClickListener(View.OnClickListener {

            val intent = Intent(context, DetailActivity::class.java).apply {

                putExtra("id", id.toString())
                putExtra("imagen", image)
                putExtra("title", title)
                putExtra("original_language", original_language)
                putExtra("release_date", release_date)
                putExtra("vote_average", vote_average.toString())
                putExtra("overview" , overview)
                putExtra("cache", "false")
            }
            context.startActivity(intent)
        })


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.adapter_movie, parent, false))
    }
    override fun getItemCount(): Int {
        return movies.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imageview = view.findViewById(R.id.imageview) as ImageView

    }
}