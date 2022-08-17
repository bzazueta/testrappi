package com.example.myrappi.data.model

import com.google.gson.annotations.SerializedName

// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json     = Json(JsonConfiguration.Stable)
// val upComing = json.parse(UpComing.serializer(), jsonString)




data class UpComingModel(

    val dates: Dates,
    val page: Long,
    val results: List<Result>,
    val total_pages: Long,
    val total_results: Long
)


data class Dates (
    val maximum: String,
    val minimum: String
)



data class Result (
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
