package com.example.myrappi.domain

import com.example.myrappi.data.MovieRepository
import com.example.myrappi.data.database.entities.UpComingMoviesEntity
import com.example.myrappi.data.model.Dates
import com.example.myrappi.data.model.Result
import com.example.myrappi.data.model.UpComingModel

import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {



    suspend operator fun invoke():UpComingModel{
        var mImage: ByteArray = byteArrayOf()

       // var converter : Converters = Converters()
        val movies = repository.getAllQuotesFromApi()
        if(movies.page > 0)
        {

            repository.clearMovies()

            for(i in movies.results.indices)
            {
                // imageBitmap = converter.getBitmapFromURL("https://image.tmdb.org/t/p/w500"+movies.results.get(i).poster_path.trim())
                // var fileName= movies.results.get(i).poster_path.replace("/","")
                //converter.mSaveMediaToStorage(imageBitmap,context)
                //mImage = converter.fromBitmap(imageBitmap!!)
                var quoteEntity_ : UpComingMoviesEntity = UpComingMoviesEntity(0,movies.results.get(i).poster_path,movies.results.get(i).title,
                movies.results.get(i).original_language,movies.results.get(i).release_date,movies.results.get(i).vote_average.toString(),
                movies.results.get(i).overview,movies.results.get(i).id,mImage)
                repository.insertMovies(quoteEntity_)
            }
//
            val resd_ = repository.getAllMoviesFromDatabase()
            resd_.toString()
            return movies
        }
        else
        {
            val list : List<Result> = emptyList()
            val dates :Dates = Dates("","")
            val movies :UpComingModel = UpComingModel(dates,0,list,0,0)
            return movies
        }



    }



}