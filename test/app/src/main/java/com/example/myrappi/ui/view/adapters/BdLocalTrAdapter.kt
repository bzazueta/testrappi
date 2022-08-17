package com.example.myrappi.ui.view.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrappi.R
import com.example.myrappi.data.database.dao.TopRatedDao
import com.example.myrappi.data.database.entities.TopRatedEntity
import com.example.myrappi.data.database.entities.UpComingMoviesEntity
import com.example.myrappi.ui.util.Converters
import com.example.myrappi.ui.view.DetailActivity

class BdLocalTrAdapter : RecyclerView.Adapter<BdLocalTrAdapter.ViewHolder>() {
    var movies = mutableListOf<TopRatedEntity>()//MutableList<String>  = ArrayList()
    lateinit var context: Context
    var converter: Converters = Converters()

    fun RecyclerAdapter(movies_ : ArrayList<TopRatedEntity>, context: Context){
        this.movies = movies_
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val image = movies.get(position).image_bitmap
        var bitmap : Bitmap = converter.toBitmap(image)

        holder.imageview.setImageBitmap(bitmap)



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