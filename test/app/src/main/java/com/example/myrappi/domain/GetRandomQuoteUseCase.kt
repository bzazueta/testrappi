package com.cursokotlin.mvvmexample.domain

import com.example.myrappi.data.MovieRepository

import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val repository: MovieRepository) {

//    suspend operator fun invoke(): Quote? {
//        val quotes = repository.getAllQuotesFromDatabase()
//        if (!quotes.isNullOrEmpty()) {
//            val randomNumber = (quotes.indices).random()
//            return quotes[randomNumber]
//        }
//        return null
//    }
}