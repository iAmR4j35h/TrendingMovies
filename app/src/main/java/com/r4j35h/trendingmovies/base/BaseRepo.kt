package com.r4j35h.trendingmovies.base

import android.app.Application
import com.r4j35h.trendingmovies.TrendingMoviesApp
import com.r4j35h.trendingmovies.network.ApiService
import javax.inject.Inject

open class BaseRepo(application: Application) {
    init {
        (application as TrendingMoviesApp).component?.inject(this)
    }

    @Inject
    lateinit var apiService: ApiService

}