package com.example.myrappi.domain

import com.example.myrappi.data.MovieRepository
import com.example.myrappi.data.model.TrailerModel
import javax.inject.Inject

class GetTrailerUseCase  @Inject constructor(private  val repository: MovieRepository){
    suspend operator fun invoke(int: Int): TrailerModel {
        return repository.getTrailers(int)


    }
}