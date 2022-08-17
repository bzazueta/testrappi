package com.example.myrappi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.myrappi.data.database.entities.TopRatedEntity
import com.example.myrappi.data.database.entities.UpComingMoviesEntity
import com.example.myrappi.data.model.FilerMovieModel

@Dao
interface MovieDao {

    @Query("SELECT * FROM upcoming_movies_table")
    suspend fun getAllMovies():List<UpComingMoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(upComingMoviesEntity: UpComingMoviesEntity)

    @Query("DELETE FROM upcoming_movies_table")
    suspend fun deleteAllMovies()

    @Query("UPDATE upcoming_movies_table SET image_bitmap = :biteArray WHERE id_movie = :id")
    suspend fun updateAllMovies(biteArray: ByteArray,id:Int)


}