package com.cursokotlin.mvvmexample.di

import com.cursokotlin.mvvmexample.data.network.MovieApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            //.baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit):MovieApiClient{
        return retrofit.create(MovieApiClient::class.java)
    }
//    fun provideQuoteApiClient(retrofit: Retrofit):MovieApiClient{
//        return retrofit.create(MovieApiClient::class.java)
//    }
}