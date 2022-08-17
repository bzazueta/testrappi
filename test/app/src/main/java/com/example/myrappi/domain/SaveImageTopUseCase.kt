package com.example.myrappi.domain

import com.example.myrappi.data.MovieRepository
import javax.inject.Inject

class SaveImageTopUseCase @Inject constructor(private  val repository : MovieRepository) {

    suspend operator fun invoke(byteArray: ByteArray,id:Int) {
        return repository.saveImageTopRated(byteArray,id)

    }
}