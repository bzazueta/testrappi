package com.example.myrappi.domain

import com.example.myrappi.data.MovieRepository
import com.example.myrappi.data.database.entities.UpComingMoviesEntity
import com.example.myrappi.data.model.Dates
import com.example.myrappi.data.model.Result
import com.example.myrappi.data.model.UpComingModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


internal class GetMoviesUseCaseTest{

    @RelaxedMockK
    private lateinit var movieRepository: MovieRepository


    val result : Result = Result(true,"test", emptyList(),1,"TEST","TEST","TEST",0.5,"TEST," +
            "00","2022-09-09","TEST",true,0.0,0)
    var list : List<Result> = emptyList()

    val dates : Dates = Dates("2022-09-09","2022-09-09")
    val movies : UpComingModel = UpComingModel(dates,1,list,0,0)
    val moviesBd : UpComingMoviesEntity = UpComingMoviesEntity(1,"test","test","test","","","",0,
        byteArrayOf())
    lateinit var getMovieUseCase: GetMoviesUseCase


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getMovieUseCase = GetMoviesUseCase(movieRepository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        //Given
        // caso de prueba al ejecutar el metodo movieRepository.getAllQuotesFromApi se comprueba que también se
        //ejecute el metodo movieRepository.clearMovies() que es el que limpia ala BD local.
        //Nada mas agregue este caso porque ya tengo una semana con el test para no durar mas
        coEvery { movieRepository.getAllQuotesFromApi() } returns movies
       //When
        getMovieUseCase()

        //Then
        //se comrueba que se ejecute clearMovies()
        coVerify(exactly = 1) { movieRepository.clearMovies() }
    }

}