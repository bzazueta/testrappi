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
import com.example.myrappi.data.model.TopRatedModel
import com.example.myrappi.ui.view.DetailActivity

class RecommendedAdapter : RecyclerView.Adapter<RecommendedAdapter.ViewHolder>() {
    lateinit var movies: TopRatedModel//MutableList<String>  = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(movies_ : TopRatedModel, context: Context){
        this.movies = movies_
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // for(i in superheros.results.indices)
        //{
        val image = "https://image.tmdb.org/t/p/w500/"+ movies.results.get(position).poster_path
        val title =  movies.results.get(position).original_title
        val original_language =  movies.results.get(position).original_language
        val release_date = movies.results.get(position).release_date
        val vote_average  = movies.results.get(position).vote_average
        val overview  = movies.results.get(position).overview
        val id  = movies.results.get(position).id

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
            //Toast.makeText(context, movies.results.get(position).poster_path, Toast.LENGTH_SHORT).show()
        })
        //holder.bind(item, context)
        // }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.adapter_movie, parent, false))
    }
    override fun getItemCount(): Int {
        return 6
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //        val superheroName = view.findViewById(R.id.tvSuperhero) as TextView
//        val realName = view.findViewById(R.id.tvRealName) as TextView
//        val publisher = view.findViewById(R.id.tvPublisher) as TextView
        val imageview = view.findViewById(R.id.imageview) as ImageView
        fun bind(superhero:String, context: Context){
//            superheroName.text = superhero.superhero
//            realName.text = superhero.realName
//            publisher.text = superhero.publisher
            //avatar.loadUrl(superhero)
            //itemView.setOnClickListener(View.OnClickListener { Toast.makeText(context, superhero, Toast.LENGTH_SHORT).show() })
        }
        fun ImageView.loadUrl(url: String) {
            Glide.with(context).load(url).into(this)
        }
    }
}