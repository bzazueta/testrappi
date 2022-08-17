package com.example.myrappi.ui.view.adapters

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Environment
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrappi.R
import com.example.myrappi.data.database.entities.UpComingMoviesEntity
import com.example.myrappi.data.model.FilerMovieModel
import com.example.myrappi.ui.util.Converters
import com.example.myrappi.ui.view.DetailActivity
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class BdLocalAdapter : RecyclerView.Adapter<BdLocalAdapter.ViewHolder>() {
    var movies = mutableListOf<UpComingMoviesEntity>()//MutableList<String>  = ArrayList()
    lateinit var context: Context
    var converter: Converters = Converters()

    var image : Bitmap? = null

    fun RecyclerAdapter(movies_: ArrayList<UpComingMoviesEntity>, context: Context) {
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