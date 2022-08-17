package com.example.myrappi.ui.view

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.mvvmexample.ui.viewmodel.MovieViewModel
import com.example.myrappi.R
import com.example.myrappi.data.database.entities.TopRatedEntity
import com.example.myrappi.data.model.TopRatedModel
import com.example.myrappi.databinding.ActivityMainBinding
import com.example.myrappi.ui.util.Conexion
import com.example.myrappi.ui.view.adapters.*
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity  : AppCompatActivity() {


     lateinit var mRecyclerView : RecyclerView
     lateinit var recyclerViewT : RecyclerView
     val mAdapter : UpComingUpMoviesAdapter = UpComingUpMoviesAdapter()
     val topRatedAdater : TopRatedAdater = TopRatedAdater()
     val recommendedAdapter : RecommendedAdapter = RecommendedAdapter()
     val filterAdapter :FilterAdapter = FilterAdapter()
     val bdLocalAdapter :BdLocalAdapter = BdLocalAdapter()
     val bdLocalTrAdapter :BdLocalTrAdapter = BdLocalTrAdapter()
     val bdLocalRAdapter : BdLocarRAdapter = BdLocarRAdapter()
     val bdLocalFilterAdapter : BdLocalFilterAdapter = BdLocalFilterAdapter()
     private var shimmerFrameLayout: ShimmerFrameLayout? = null
     private lateinit var binding: ActivityMainBinding
     val arrayLg :ArrayList<String> = ArrayList()
     lateinit var movies : TopRatedModel
     var arrayMoviesBd :ArrayList<TopRatedEntity> = ArrayList()
     var wifi :Boolean = false
     private val movieViewModel: MovieViewModel by viewModels()
     var conexion : Conexion = Conexion()
     var activity = this@MainActivity
    var bol : Boolean= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //binding.viewContainer.visibility = View.VISIBLE
        binding.shimmerFrameLayout?.startShimmerAnimation()
        binding.shimmerFrameLayout.visibility = View.VISIBLE

        val recyclerView: RecyclerView = findViewById(com.example.myrappi.R.id.recyclerView)
        val window: Window = this.getWindow()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(ContextCompat.getColor(this, com.example.myrappi.R.color.black))

//        try {
//            val field: Field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
//            field.setAccessible(true)
//            field.set(null, 100 * 1024 * 1024) //the 100MB is the new size
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }



         wifi = conexion.isNetworkConnected(getApplicationContext());
         if(wifi)
         {
             movieViewModel.onCreate(this)
         }
        else{
             movieViewModel.getMovieBd()
        }

        movieViewModel.is_.observe(this,{
            it.toList()

        })

        movieViewModel.quoteModel.observe(this, Observer {
          //  binding.tvQuote.text = it.dates.maximum
            //binding.tvAuthor.text = it.dates.maximum
            binding.shimmerFrameLayout?.stopShimmerAnimation()
            binding.shimmerFrameLayout?.visibility = View.GONE
            binding.tvQuote.visibility = View.VISIBLE
            binding.tvAuthor.visibility = View.VISIBLE
            binding.lblRecomendados.visibility = View.VISIBLE
            binding.spinner.visibility = View.VISIBLE
            binding.lblSelecciona.visibility = View.VISIBLE
            binding.lblTendencia.visibility = View.VISIBLE
            recyclerView.visibility = View.VISIBLE
                 mAdapter.RecyclerAdapter(it,this)
                 binding.recyclerView.adapter = mAdapter
                 movieViewModel.getTopRated()

        })

        movieViewModel.topRatedModel.observe(this,Observer{

            binding.loading.isVisible = false
            topRatedAdater.RecyclerAdapter(it,this)
            binding.recyclerViewT.visibility = View.VISIBLE
            binding.recyclerViewT.adapter = topRatedAdater

            movieViewModel.getFilter(it)
            movies = it
            recommendedAdapter.RecyclerAdapter(it,this)
            binding.recyclerViewR.visibility = View.VISIBLE
            binding.recyclerViewR.layoutManager = GridLayoutManager(applicationContext,2)
            binding.recyclerViewR.adapter = recommendedAdapter



        })

        movieViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })

        movieViewModel.filterLg.observe(this,Observer{
            it.toString()
            val adapter = ArrayAdapter(
                this@MainActivity,
                R.layout.spinner_item,
                it
            )
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            binding.spinner.setAdapter(adapter)

            binding.spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val seleccion = parent.selectedItem.toString()
                    if(!seleccion.equals("Filtrar Por Idioma"))
                    {
                        movieViewModel.getFilterMovie(movies,seleccion)

                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            })

            movieViewModel.getMoviesUpComing(this)

        })

        movieViewModel.filterMovie.observe(this,Observer{

            it.toString()

            binding.recyclerViewR.removeAllViews()
            filterAdapter.RecyclerAdapter(it,this@MainActivity)
             binding.recyclerViewR.layoutManager = GridLayoutManager(applicationContext,2)
             binding.recyclerViewR.adapter = filterAdapter
        })

        movieViewModel.bdMovie.observe(this,Observer{


            it.toString()
            binding.shimmerFrameLayout?.stopShimmerAnimation()
            binding.shimmerFrameLayout?.visibility = View.GONE

            binding.tvQuote.visibility = View.VISIBLE
            binding.tvAuthor.visibility = View.VISIBLE

            recyclerView.visibility = View.VISIBLE
            bdLocalAdapter.RecyclerAdapter(it,this)
            binding.recyclerView.adapter = bdLocalAdapter
            //movieViewModel.getTopRated()

            movieViewModel.getMovieTopRatedBd()
        })

        movieViewModel.bdMovieTopRated.observe(this,Observer{

            it.toString()
            binding.lblSelecciona.visibility = View.VISIBLE
            binding.lblTendencia.visibility = View.VISIBLE
            binding.recyclerViewT.visibility = View.VISIBLE
            bdLocalTrAdapter.RecyclerAdapter(it,this)
            binding.recyclerViewT.adapter = bdLocalTrAdapter
            binding.lblRecomendados.visibility = View.VISIBLE
            binding.spinner.visibility = View.VISIBLE
            movieViewModel.getFilterBd(it)
            arrayMoviesBd = it
            binding.recyclerViewR.visibility = View.VISIBLE
            binding.recyclerViewR.layoutManager = GridLayoutManager(applicationContext,2)
            bdLocalRAdapter.RecyclerAdapter(it,this)
            binding.recyclerViewR.adapter = bdLocalRAdapter

            binding.loading.isVisible= false




        })

        movieViewModel.filterLgBd.observe(this,Observer{
            it.toString()
            val adapter = ArrayAdapter(
                this@MainActivity,
                R.layout.spinner_item,
                it
            )
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            binding.spinner.setAdapter(adapter)

            binding.spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val seleccion = parent.selectedItem.toString()
                    if(!seleccion.equals("Filtrar Por Idioma"))
                    {
                        movieViewModel.getFilterMovieBd(arrayMoviesBd,seleccion)

                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            })


        })

        movieViewModel.filterMovieBd.observe(this,Observer{

            it.toString()

            binding.recyclerViewR.removeAllViews()
            bdLocalFilterAdapter.RecyclerAdapter(it,this@MainActivity)
            binding.recyclerViewR.layoutManager = GridLayoutManager(applicationContext,2)
            binding.recyclerViewR.adapter = bdLocalFilterAdapter
        })

        movieViewModel.saveImages.observe(this,Observer{

            if(it)
            {
                movieViewModel.getMoviesTopRated(this)
               // Toast.makeText(this,"Imegen guardada",Toast.LENGTH_SHORT).show()
            }
        })

        movieViewModel.saveImagesTop.observe(this ,Observer{

            bol=true
          //  movieViewModel.getTrailerAndSaveLocalFile(it,this)

        })

        movieViewModel.saveFileVideo.observe(this,Observer{


        })

        //  binding.viewContainer.setOnClickListener { upComingViewModel.randomQuote() }

    }

    override fun onBackPressed() {

        if(bol==true)
        {

            super.onBackPressed()
        }
        else{
            Toast.makeText(this,"Descargando información a local...",Toast.LENGTH_SHORT).show()
        }

    }
}