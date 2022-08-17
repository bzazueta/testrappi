package com.example.myrappi.domain.model


import com.example.myrappi.data.database.entities.UpComingMoviesEntity
import com.example.myrappi.data.model.Dates
import com.example.myrappi.data.model.Result
import com.example.myrappi.data.model.UpComingModel

data class Upcoming(val dates: Dates, val page:Long ,val result: List<Result>,val total_pages :Long,val total_results:Long)
var empty = byteArrayOf()
fun UpComingModel.toDomain() = UpComingModel(dates,page, results,total_pages,total_results)
fun UpComingMoviesEntity.toDomain() = UpComingMoviesEntity(0,"imagen","","","","","",0, empty)