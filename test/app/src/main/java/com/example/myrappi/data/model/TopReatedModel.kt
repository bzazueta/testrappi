package com.example.myrappi.data.model





data class TopRatedModel (
    val page: Long,
    val results: List<Resultp>,
    val total_pages: Long,
    val total_results: Long
)


data class Resultp (
    val adult: Boolean,


    val backdrop_path: String,
    val genre_ids: List<Long>,
    val id: Long,


    val original_language: String,


    val original_title: String,

    val overview: String,
    val popularity: Double,


    val poster_path: String,


    val release_date: String,

    val title: String,
    val video: Boolean,


    val vote_average: Double,


    val vote_count: Long
)

