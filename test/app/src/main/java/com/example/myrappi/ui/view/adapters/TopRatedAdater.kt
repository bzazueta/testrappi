package com.example.myrappi.ui.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrappi.R
import com.example.myrappi.data.model.TopRatedModel
import com.example.myrappi.data.model.UpComingModel

class TopRatedAdater  : RecyclerView.Adapter<TopRatedAdater.ViewHolder>() {
    lateinit var movies: TopRatedModel//MutableList<String>  = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(movies_ : TopRatedModel, context: Context){
        this.movies = movies_
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // for(i in superheros.results.indices)
        //{
        val item = "https://image.tmdb.org/t/p/w500/"+ movies.results.get(position).poster_path
        holder.bind(item, context)
        // }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.adapter_movie, parent, false))
    }
    override fun getItemCount(): Int {
        return movies.results.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //        val superheroName = view.findViewById(R.id.tvSuperhero) as TextView
//        val realName = view.findViewById(R.id.tvRealName) as TextView
//        val publisher = view.findViewById(R.id.tvPublisher) as TextView
        val avatar = view.findViewById(R.id.imageview) as ImageView
        fun bind(superhero:String, context: Context){
//            superheroName.text = superhero.superhero
//            realName.text = superhero.realName
//            publisher.text = superhero.publisher
            avatar.loadUrl(superhero)
            itemView.setOnClickListener(View.OnClickListener { Toast.makeText(context, superhero, Toast.LENGTH_SHORT).show() })
        }
        fun ImageView.loadUrl(url: String) {
            Glide.with(context).load(url).into(this)
        }
    }
}