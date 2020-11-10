package com.r4j35h.trendingmovies.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.r4j35h.trendingmovies.TrendingMoviesApp
import com.r4j35h.trendingmovies.network.ApiConstants
import com.r4j35h.trendingmovies.network.ApiService
import com.r4j35h.trendingmovies.ui.MoviesRepo
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiServiceModule(var application: TrendingMoviesApp) {

    @Provides
    fun apiService(retofit: Retrofit): ApiService {
        return retofit.create(ApiService::class.java)
    }

    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)

            .baseUrl(ApiConstants.BASE_URL)
            .build()
    }

    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
//        gsonBuilder.registerTypeAdapter(DateTime::class.java, DateTimeConverter())
        return gsonBuilder.create()
    }

    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(20, TimeUnit.SECONDS)
//            .cache(cache)
            .build()
    }

    @Provides
    fun loggingInterceptor(logger: HttpLoggingInterceptor.Logger): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(logger)
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun getContext(): Context = application


    @Provides
    fun getLogger(): HttpLoggingInterceptor.Logger {
        val logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("OkHttp").d(message)
            }
        }
        return logger
    }
    @Provides
    fun getMovieRepo():MoviesRepo{
        return  MoviesRepo(application)
    }
}