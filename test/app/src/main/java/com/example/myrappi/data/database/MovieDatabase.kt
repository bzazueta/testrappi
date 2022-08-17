package com.cursokotlin.mvvmexample.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myrappi.data.database.dao.MovieDao
import com.example.myrappi.data.database.dao.TopRatedDao
import com.example.myrappi.data.database.entities.TopRatedEntity
import com.example.myrappi.data.database.entities.UpComingMoviesEntity

@Database(entities = [UpComingMoviesEntity::class,TopRatedEntity::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    //abstract fun getQuoteDao():QuoteDao

    abstract fun getMovieDao():MovieDao

    abstract fun getMovieTopRatedDao():TopRatedDao
}