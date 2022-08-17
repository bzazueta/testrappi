package com.example.myrappi.data.database.dao

import androidx.room.*
import com.example.myrappi.data.database.entities.TopRatedEntity
import com.example.myrappi.data.database.entities.UpComingMoviesEntity

@Dao
interface TopRatedDao {

    @Query("SELECT * FROM toprated_table")
    suspend fun getAllMovies():List<TopRatedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMoviesTopRated(topRatedEntity: TopRatedEntity)

    @Query("DELETE FROM toprated_table")
    suspend fun deleteAllMovies()

    @Query("UPDATE toprated_table SET image_bitmap = :biteArray WHERE id_movie = :id")
    suspend fun updateAllMovies(biteArray: ByteArray,id:Int)
}