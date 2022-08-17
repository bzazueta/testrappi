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
import com.example.myrappi.data.database.entities.TopRatedEntity
import com.example.myrappi.data.model.TopRatedModel
import com.example.myrappi.ui.util.Converters
import com.example.myrappi.ui.util.VariablesGlobales
import com.example.myrappi.ui.view.DetailActivity

class BdLocarRAdapter : RecyclerView.Adapter<BdLocarRAdapter.ViewHolder>() {
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

        holder.imageview.setOnClickListener(View.OnClickListener {

            val image = movies.get(position).image_bitmap
            var bitmap : Bitmap = converter.toBitmap(image)
            val title =  movies.get(position).title
            val original_language =   movies.get(position).original_language
            val release_date =  movies.get(position).release_date
            val vote_average  =  movies.get(position).vote_average
            val overview  = movies.get(position).overview
            val id  =  movies.get(position).id

            VariablesGlobales.setBitmap(bitmap)
            val intent = Intent(context, DetailActivity::class.java).apply {

                putExtra("id", id.toString())
                putExtra("imagen", "")
                putExtra("title",  title)
                putExtra("original_language", original_language)
                putExtra("release_date", release_date)
                putExtra("vote_average", vote_average.toString())
                putExtra("overview" , overview)
                putExtra("cache", "true")
            }
            context.startActivity(intent)
        })


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.adapter_movie, parent, false))
    }
    override fun getItemCount(): Int {
        return return 6
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imageview = view.findViewById(R.id.imageview) as ImageView



    }
}