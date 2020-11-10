package com.r4j35h.trendingmovies.ui

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.r4j35h.trendingmovies.base.BaseRepo
import com.r4j35h.trendingmovies.network.ApiConstants
import com.r4j35h.trendingmovies.network.model.MovieModelResponse
import io.reactivex.schedulers.Schedulers

class MoviesRepo(application: Application):BaseRepo(application) {
    val data=MutableLiveData<MovieModelResponse?>()

    fun fetchMovies(page:Int){
        apiService.getTrendingMovies(ApiConstants.API_KEY,page).subscribeOn(Schedulers.io())
            .subscribe({
                data.postValue(it)
            },{
                it.printStackTrace()
                data.postValue(null)
            })
    }

    fun getMoviesList():MutableLiveData<MovieModelResponse?>{
        return data
    }

}