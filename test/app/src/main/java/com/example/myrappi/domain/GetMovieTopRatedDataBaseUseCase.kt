package com.example.myrappi.domain

import com.example.myrappi.data.MovieRepository
import com.example.myrappi.data.database.entities.TopRatedEntity
import javax.inject.Inject

class GetMovieTopRatedDataBaseUseCase @Inject constructor(val repository: MovieRepository){

    suspend operator fun invoke(): List<TopRatedEntity> {
        return repository.getAllTopRatedMoviesFromDatabase()

    }
}