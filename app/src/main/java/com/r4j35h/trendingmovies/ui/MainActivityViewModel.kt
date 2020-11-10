package com.r4j35h.trendingmovies.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.r4j35h.trendingmovies.TrendingMoviesApp
import com.r4j35h.trendingmovies.network.ApiService
import com.r4j35h.trendingmovies.network.model.MovieModelResponse
import javax.inject.Inject

class MainActivityViewModel(application: Application) :AndroidViewModel(application){
    init {
        (application as TrendingMoviesApp).component?.inject(this)
    }
    @Inject
    lateinit var  moviesRepo:MoviesRepo

    var page=0

    var moviesList=ArrayList<MovieModelResponse.MovieModel>()
    var isDataLoading=false


    fun fetchMovies()=moviesRepo.fetchMovies(page+1)
    fun getMovies()=moviesRepo.getMoviesList()
}