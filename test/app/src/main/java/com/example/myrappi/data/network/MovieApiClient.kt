package com.cursokotlin.mvvmexample.data.network

import com.cursokotlin.mvvmexample.data.model.QuoteModel
import com.example.myrappi.data.model.TopRatedModel
import com.example.myrappi.data.model.TrailerModel
import com.example.myrappi.data.model.UpComingModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiClient {
    //@GET("/.json")
    @GET("/3/movie/upcoming?api_key=933dc8e875378a25bceedc51ed51bc42&language=en-US&page=2")
    suspend fun getAllQuotes(): Response<UpComingModel>

    @GET("/3/movie/top_rated?api_key=933dc8e875378a25bceedc51ed51bc42&language=en-US&page=1")
    suspend fun getAllTopRated(): Response<TopRatedModel>

    @GET("/3/movie/{id}}/videos?api_key=933dc8e875378a25bceedc51ed51bc42&language=en-US")
    suspend fun getTrailerId(@Path("id") id: Int?, @Query("a_param") aParam: String?): Response<TrailerModel>
}