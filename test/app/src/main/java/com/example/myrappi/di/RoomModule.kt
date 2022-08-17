package com.cursokotlin.mvvmexample.di

import android.content.Context
import androidx.room.Room
import com.cursokotlin.mvvmexample.data.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object RoomModule {

    private const val QUOTE_DATABASE_NAME = "movie_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MovieDatabase::class.java, QUOTE_DATABASE_NAME).build()

    //@Singleton
    //@Provides
   // fun provideQuoteDao(db: MovieDatabase) = db.getQuoteDao()

    @Singleton
    @Provides
    fun provideMovieDao(db: MovieDatabase) = db.getMovieDao()

    @Singleton
    @Provides
    fun provideMovieTopRatedDao(db: MovieDatabase) = db.getMovieTopRatedDao()
}