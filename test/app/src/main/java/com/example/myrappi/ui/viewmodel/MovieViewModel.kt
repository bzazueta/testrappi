package com.cursokotlin.mvvmexample.ui.viewmodel

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrappi.domain.GetMoviesUseCase
import com.example.myrappi.data.database.entities.TopRatedEntity
import com.example.myrappi.data.database.entities.UpComingMoviesEntity
import com.example.myrappi.data.model.FilerMovieModel
import com.example.myrappi.data.model.TopRatedModel
import com.example.myrappi.data.model.TrailerModel
import com.example.myrappi.data.model.UpComingModel
import com.example.myrappi.domain.*
import com.example.myrappi.ui.util.Converters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val getTrailerUseCase: GetTrailerUseCase,
    private val getMovieDataBaseUseCase: GetMovieDataBaseUseCase,
    private val  getMovieTopRatedDataBaseUseCase: GetMovieTopRatedDataBaseUseCase,
    private val saveImageUpUseCase: SaveImageUpUseCase,
    private val saveImageTopUseCase: SaveImageTopUseCase
) : ViewModel() {

    var mImage: ByteArray = byteArrayOf()
    var imageBitmap : Bitmap? = null
    var converter : Converters = Converters()

    val quoteModel = MutableLiveData<UpComingModel>()
    val getMovieUpModel = MutableLiveData<UpComingModel>()
    val topRatedModel = MutableLiveData<TopRatedModel>()
    val trailerModel = MutableLiveData<TrailerModel>()
    val filterLg = MutableLiveData<ArrayList<String>>()
    val filterMovie = MutableLiveData<ArrayList<FilerMovieModel>>()
    val bdMovie = MutableLiveData<ArrayList<UpComingMoviesEntity>>()
    val bdMovieTopRated = MutableLiveData<ArrayList<TopRatedEntity>>()
    val filterLgBd = MutableLiveData<ArrayList<String>>()
    val filterMovieBd = MutableLiveData<ArrayList<FilerMovieModel>>()
    val isLoading = MutableLiveData<Boolean>()
    val saveImages = MutableLiveData<Boolean>()
    val saveFileVideo = MutableLiveData<Boolean>()
    val saveImagesTop = MutableLiveData<TopRatedModel>()
    val is_ = MutableLiveData<String>()
    val arrayLg : ArrayList<String> = ArrayList()
    val arrayMovies : ArrayList<FilerMovieModel> = ArrayList()
    lateinit var filerMovieModel : FilerMovieModel


    fun onCreate(context: Context) {
        viewModelScope.launch {


            isLoading.postValue(true)
            val result = getMoviesUseCase()
            // result.toString()
            quoteModel.postValue(result)
            //isLoading.postValue(false)

//            if (!result.page > 0) {
//                quoteModel.postValue(result)
//                isLoading.postValue(false)
//            }
        }
    }

    fun getTopRated(){
        viewModelScope.launch {
           // isLoading.postValue(true)
            val result = getTopRatedUseCase()
            // result.toString()
            topRatedModel.postValue(result)
            isLoading.postValue(false)

        }
    }

    fun getTrailer(id: Int){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getTrailerUseCase(id)
            // result.toString()

            trailerModel.postValue(result)
            isLoading.postValue(false)

        }
    }

    fun getFilter(topRatedModel: TopRatedModel){
        viewModelScope.launch {

            arrayLg.add("Filtrar Por Idioma")
            for(i in 0..5)
            {
                var lg : String = topRatedModel.results.get(i).original_language
                if (!arrayLg.contains(lg))
                {
                    arrayLg.add(lg)
                }
            }
            filterLg.postValue(arrayLg)


        }
    }

    fun getFilterMovie(topRatedModel: TopRatedModel,lg : String){
        viewModelScope.launch {

            arrayMovies.clear()
            for(i in 0..5)
            {

                if(topRatedModel.results.get(i).original_language.equals(lg))
                {
                    filerMovieModel = FilerMovieModel(
                         topRatedModel.results.get(i).id,
                         topRatedModel.results.get(i).poster_path,
                         topRatedModel.results.get(i).title,
                         topRatedModel.results.get(i).original_language,
                         topRatedModel.results.get(i).release_date,
                         topRatedModel.results.get(i).vote_average.toString(),
                         topRatedModel.results.get(i).overview.toString(),
                         topRatedModel.results.get(i).id, byteArrayOf()
                    )

                    arrayMovies.add(filerMovieModel)
                }



            }
            filterMovie.postValue(arrayMovies)


        }
    }

    fun getMovieBd(){
        viewModelScope.launch {

            isLoading.postValue(true)
            val result = getMovieDataBaseUseCase()
            // result.toString()
            bdMovie.postValue(result as ArrayList<UpComingMoviesEntity>?)

        }
    }

    fun getMovieTopRatedBd(){
        viewModelScope.launch {

            //isLoading.postValue(true)
            val result = getMovieTopRatedDataBaseUseCase()
             result.toString()
            bdMovieTopRated.postValue(result as ArrayList<TopRatedEntity>?)
        }
    }

    fun getFilterBd(topRatedEntity: List<TopRatedEntity>){
        viewModelScope.launch {

            arrayLg.add("Filtrar Por Idioma")
            for(i in 0..5)
            {
                var lg : String = topRatedEntity.get(i).original_language
                if (!arrayLg.contains(lg))
                {
                    arrayLg.add(lg)
                }
            }
            filterLgBd.postValue(arrayLg)


        }
    }

    fun getMoviesUpComing(context: Context){
        viewModelScope.launch {

            val result = getMoviesUseCase()


            for(i in result.results.indices)
            {
                imageBitmap = converter.getBitmapFromURL("https://image.tmdb.org/t/p/w500"+result.results.get(i).poster_path.trim())
                var id = result.results.get(i).id.toInt()
                //converter.mSaveMediaToStorage(imageBitmap,context)
                mImage = converter.fromBitmap(imageBitmap!!)
                val response = saveImageUpUseCase(mImage,id)
            }
            //getMovieUpModel.postValue(it)
            saveImages.postValue(true)


        }
    }

    fun getMoviesTopRated(context: Context){
        viewModelScope.launch {

            val result = getTopRatedUseCase()


            for(i in result.results.indices)
            {
                imageBitmap = converter.getBitmapFromURL("https://image.tmdb.org/t/p/w500"+result.results.get(i).poster_path.trim())
                var id = result.results.get(i).id.toInt()
               // converter.mSaveMediaToStorage(imageBitmap,context)
                mImage = converter.fromBitmap(imageBitmap!!)
                val response = saveImageTopUseCase(mImage,id)
            }
            //getMovieUpModel.postValue(it)

           saveImagesTop.postValue(result)

        }
    }

    fun getFilterMovieBd(topRatedModel: List<TopRatedEntity>,lg : String){
        viewModelScope.launch {

            arrayMovies.clear()
            for(i in 0..5)
            {

                if(topRatedModel.get(i).original_language.equals(lg))
                {
                    filerMovieModel = FilerMovieModel(
                        topRatedModel.get(i).id,
                        topRatedModel.get(i).image,
                        topRatedModel.get(i).title,
                        topRatedModel.get(i).original_language,
                        topRatedModel.get(i).release_date,
                        topRatedModel.get(i).vote_average.toString(),
                        topRatedModel.get(i).overview.toString(),
                        topRatedModel.get(i).id,
                        topRatedModel.get(i).image_bitmap
                    )

                    arrayMovies.add(filerMovieModel)
                }



            }
            filterMovieBd.postValue(arrayMovies)


        }
    }

    fun saveImageBd(topRatedEntity: List<TopRatedEntity>){
        viewModelScope.launch {


            arrayLg.add("Filtrar Por Idioma")
            for(i in 0..5)
            {
                var lg : String = topRatedEntity.get(i).original_language
                if (!arrayLg.contains(lg))
                {
                    arrayLg.add(lg)
                }
            }
            filterLgBd.postValue(arrayLg)


        }
    }

    fun getTrailerAndSaveLocalFile(topRatedModel: TopRatedModel,context: Context){
        viewModelScope.launch {
           // isLoading.postValue(true)
            var id : Long =0
            var keyTrailer : String =""
            var url :String = ""
            for(i in topRatedModel.results.indices)
            {
                id = topRatedModel.results.get(i).id
                val result = getTrailerUseCase(id.toInt())
                result.toString()
                var official :Boolean = false

                    if(official == false) {
                        if (result.results.get(i).official == true) {
                            official = true
                            keyTrailer = result.results.get(i).key.toString()
                        }
                        if (result.results.get(i).official == false) {
                            official = true
                            keyTrailer = result.results.get(i).key.toString()
                        }
                    }

                   url ="https://www.youtube.com/watch?v="+keyTrailer
                   url.toString()
                   converter.video(context)



            }





            saveFileVideo.postValue(true)


        }
    }

//    fun randomQuote() {
//        viewModelScope.launch {
//            isLoading.postValue(true)
//            val quote = getRandomQuoteUseCase()
//            if (quote != null) {
//                quoteModel.value = quote
//            }
//            isLoading.postValue(false)
//        }
//    }
}