package com.example.myrappi.domain

import com.example.myrappi.data.MovieRepository
import com.example.myrappi.data.database.entities.TopRatedEntity
import com.example.myrappi.data.model.TopRatedModel
import javax.inject.Inject

class GetTopRatedUseCase @Inject constructor(private  val repository: MovieRepository) {
    suspend operator fun invoke(): TopRatedModel {
        var mImage: ByteArray = byteArrayOf()
        val movies = repository.getAllTopRates()
        if(movies.page > 0) {

            repository.clearTopRatedMovies()

            for (i in movies.results.indices) {
                var topRatedEntity_: TopRatedEntity = TopRatedEntity(
                    0,
                    movies.results.get(i).poster_path,
                    movies.results.get(i).title,
                    movies.results.get(i).original_language,
                    movies.results.get(i).release_date,
                    movies.results.get(i).vote_average.toString(),
                    movies.results.get(i).overview, movies.results.get(i).id,
                    mImage
                )


                repository.insertTopRatedMovies(topRatedEntity_)
            }
//
            val resd_ = repository.getAllTopRatedMoviesFromDatabase()
            resd_.toString()
             movies
        }
        return movies
    }

}