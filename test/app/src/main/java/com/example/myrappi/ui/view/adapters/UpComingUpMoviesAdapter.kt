package com.example.myrappi.ui.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrappi.R
import com.example.myrappi.data.model.UpComingModel
import com.example.myrappi.ui.view.DetailActivity

class UpComingUpMoviesAdapter  : RecyclerView.Adapter<UpComingUpMoviesAdapter.ViewHolder>() {
    lateinit var superheros: UpComingModel//MutableList<String>  = ArrayList()
    lateinit var context: Context
    fun RecyclerAdapter(superheros : UpComingModel, context: Context){
        this.superheros = superheros
        this.context = context
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       // for(i in superheros.results.indices)
        //{
            val imagen= "https://image.tmdb.org/t/p/w500"+ superheros.results.get(position).poster_path
            Glide.with(context).load(imagen).into(holder.avatar)

           // holder.bind(item, context)
       // }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.adapter_movie, parent, false))
    }
    override fun getItemCount(): Int {
        return superheros.results.size
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
           // avatar.loadUrl(superhero)
            //itemView.setOnClickListener(View.OnClickListener { Toast.makeText(context, superhero, Toast.LENGTH_SHORT).show() })
        }
        fun ImageView.loadUrl(url: String) {
            Glide.with(context).load(url).into(this)
        }
    }
}