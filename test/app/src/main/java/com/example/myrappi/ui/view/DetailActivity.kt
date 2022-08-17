package com.example.myrappi.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.cursokotlin.mvvmexample.ui.viewmodel.MovieViewModel
import com.example.myrappi.R
import com.example.myrappi.databinding.ActivityDetailBinding
import com.example.myrappi.ui.util.Conexion
import com.example.myrappi.ui.util.Converters
import com.example.myrappi.ui.util.VariablesGlobales
import com.google.android.youtube.player.YouTubeStandalonePlayer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    var imagen:String = ""
    var overview : String = ""
    var title_:String = ""
    var original_language:String = ""
    var release_date  :String = ""
    var vote_average :String = ""
    var id : String = ""
    var  cache: String = ""
    var keyTrailer : String = ""
    lateinit var imageView :ImageView
    var wifi :Boolean = false
    private lateinit var binding: ActivityDetailBinding
    private val movieViewModel: MovieViewModel by viewModels()
    var conexion : Conexion = Conexion()
    lateinit var imagen_bitmap :ByteArray
    var converters : Converters = Converters()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val window: Window = this.getWindow()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(ContextCompat.getColor(this, com.example.myrappi.R.color.black))

        imageView = findViewById<ImageView>(R.id.imageView)
        imagen = if (intent.getStringExtra("imagen")== null) "" else intent.getStringExtra("imagen")!!
        title = intent.getStringExtra("title")
        original_language  = intent.getStringExtra("original_language").toString()
        release_date = intent.getStringExtra("release_date").toString()
        vote_average = intent.getStringExtra("vote_average").toString()
        overview = intent.getStringExtra("overview").toString()
        cache = intent.getStringExtra("cache").toString()
        id = intent.getStringExtra("id").toString()
       // imagen_bitmap = if (intent.getByteArrayExtra("imagen_bitmap")== null) byteArrayOf()  else intent.getByteArrayExtra("imagen_bitmap")!!

        if(cache.equals("false"))
        {
            Glide.with(this).load(imagen)
                .centerCrop()
                .into(imageView)
        }
        if(cache.equals("true"))
        {
           // var bitmap : Bitmap = converters.toBitmap(imagen_bitmap)
            imageView.setImageBitmap(VariablesGlobales.getBitmap())
        }


        binding.lblTitle.text = title
        binding.lblDate.text = release_date
        binding.lblVote.text = vote_average
        binding.lblLanguaje.text = original_language
        binding.lblOverView.text = overview

        if(cache.equals("false"))
        {
            movieViewModel.getTrailer(id.toInt())
        }


        movieViewModel.trailerModel.observe(this,Observer{

            it.toString()
            var official :Boolean = false
            for(i in it.results.indices)
            {
                if(official == false) {
                if (it.results.get(i).official == true) {
                    official = true
                    keyTrailer = it.results.get(i).key.toString()
                }
                if (it.results.get(i).official == false)
                {
                    official = true
                    keyTrailer = it.results.get(i).key.toString()
                }
              }
            }



        })

        binding.btnVerTrailer.setOnClickListener(View.OnClickListener {

            wifi = conexion.isNetworkConnected(getApplicationContext());
            if(wifi) {
                try {
                    openYoutubeStandAlonePlayer(keyTrailer, true)
                }catch (ex:Exception)
                {
                //   ex.toString()
//                    val intent = Intent(this, TrailerActivity::class.java).apply {
//
//                    }
//                    startActivity(intent)
                }
            }else{
                Toast.makeText(this, "No es posible repodrucir el trailer asegurate de tener conexión a internet e intenta de nuevo", Toast.LENGTH_SHORT).show()
            }

      })


    }

    fun openYoutubeStandAlonePlayer(VideoID: String, autoplay: Boolean = false, lightMode: Boolean = false) {
        val intent = YouTubeStandalonePlayer.createVideoIntent(
            this@DetailActivity,
            "AIzaSyBfj3oF1gNXc8vt8fJe2yRIVUGHOQ4Yh68", //Developer Api Key
            VideoID,
            0, //startIndex
            autoplay,
            true
        )
        startActivity(intent)
    }
}