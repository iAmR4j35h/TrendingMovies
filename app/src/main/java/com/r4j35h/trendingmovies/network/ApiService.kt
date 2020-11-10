package com.r4j35h.trendingmovies.network


import com.r4j35h.trendingmovies.network.model.MovieModelResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("trending/movies/day")
    fun getTrendingMovies(
        @Query("api_key") token: String,
        @Query("page") page: Int
    ): Single<MovieModelResponse>

}