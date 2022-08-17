package com.example.myrappi.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myrappi.data.model.FilerMovieModel

@Entity(tableName = "toprated_table")
data class TopRatedEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "image") val image: String = "",
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "original_language") val original_language: String = "",
    @ColumnInfo(name = "release_date") val release_date: String = "",
    @ColumnInfo(name = "vote_average") val vote_average: String = "",
    @ColumnInfo(name = "overview") val overview: String = "",
    @ColumnInfo(name = "id_movie") val id_movie: Long = 0,
    @ColumnInfo(name = "image_bitmap") val image_bitmap: ByteArray,
)

fun FilerMovieModel.toDatabaseTop() = TopRatedEntity(
    id = id, image =  image, title = title,original_language,
    release_date =release_date, vote_average =vote_average,
    overview = overview,id_movie=id_movie,image_bitmap =image_bitmap)