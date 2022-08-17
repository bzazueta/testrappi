package com.example.myrappi.domain

import com.example.myrappi.data.MovieRepository
import com.example.myrappi.data.database.entities.UpComingMoviesEntity
import javax.inject.Inject

class GetMovieDataBaseUseCase @Inject constructor(private  val repository:  MovieRepository){

    suspend operator fun invoke(): List<UpComingMoviesEntity> {
        return repository.getAllMoviesFromDatabase()

    }
}