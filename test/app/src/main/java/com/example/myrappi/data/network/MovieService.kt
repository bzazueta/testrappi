package com.cursokotlin.mvvmexample.data.network

import android.util.Log
import com.example.myrappi.data.model.TopRatedModel
import com.example.myrappi.data.model.TrailerModel
import com.example.myrappi.data.model.UpComingModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(private val api:MovieApiClient) {

    suspend fun getQuotes(): UpComingModel {
        return withContext(Dispatchers.IO) {
            val response = api.getAllQuotes()
            Log.v("json", response.body().toString());
            response.body()!!
        }
    }

    suspend fun getTopRates(): TopRatedModel {
        return withContext(Dispatchers.IO) {
            val response = api.getAllTopRated()
            Log.v("json", response.body().toString());
            response.body()!!
        }
    }

    suspend fun getTrailers(id: Int): TrailerModel {
        return withContext(Dispatchers.IO) {
            val response = api.getTrailerId(id,"")
            Log.v("json", response.body().toString());
            response.body()!!
        }
    }

}