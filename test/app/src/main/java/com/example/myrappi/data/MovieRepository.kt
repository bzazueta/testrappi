package com.example.myrappi.data

import com.cursokotlin.mvvmexample.data.network.MovieService

import com.example.myrappi.data.database.dao.MovieDao
import com.example.myrappi.data.database.dao.TopRatedDao
import com.example.myrappi.data.database.entities.TopRatedEntity
import com.example.myrappi.data.database.entities.UpComingMoviesEntity
import com.example.myrappi.data.model.TopRatedModel
import com.example.myrappi.data.model.TrailerModel
import com.example.myrappi.data.model.UpComingModel
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieService,

    private val movieDao : MovieDao,
    private val topRatedDao : TopRatedDao
) {

    suspend fun getAllQuotesFromApi():UpComingModel {
        val response: UpComingModel = api.getQuotes()
        return response
        // return response.map { it.toDomain() }
    }

    suspend fun getAllTopRates() :TopRatedModel{
        val response :TopRatedModel = api.getTopRates()
        return response
    }

    suspend fun getTrailers(id: Int) :TrailerModel{
        val response :TrailerModel = api.getTrailers(id)
        return response
    }


    suspend fun getAllMoviesFromDatabase(): List<UpComingMoviesEntity> {
        val response: List<UpComingMoviesEntity> = movieDao.getAllMovies()
        return response
    }

    suspend fun insertMovies(upComingMoviesEntity: UpComingMoviesEntity){
        movieDao.insertAllMovies(upComingMoviesEntity)
    }

    suspend fun clearMovies(){
        movieDao.deleteAllMovies()
    }

    suspend fun saveImage(byteArray: ByteArray,id: Int){
        movieDao.updateAllMovies(byteArray,id)
    }

    suspend fun getAllTopRatedMoviesFromDatabase(): List<TopRatedEntity> {
        val response: List<TopRatedEntity> = topRatedDao.getAllMovies()
        return response
    }

    suspend fun insertTopRatedMovies(topRatedEntity: TopRatedEntity){
        topRatedDao.insertAllMoviesTopRated(topRatedEntity)
    }

    suspend fun clearTopRatedMovies(){
        topRatedDao.deleteAllMovies()
    }

    suspend fun saveImageTopRated(byteArray: ByteArray,id: Int){
        topRatedDao.updateAllMovies(byteArray,id)
    }
}