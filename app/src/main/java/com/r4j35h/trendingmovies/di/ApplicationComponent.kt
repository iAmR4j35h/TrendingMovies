package com.r4j35h.trendingmovies.di

import com.r4j35h.trendingmovies.TrendingMoviesApp
import com.r4j35h.trendingmovies.base.BaseActivity
import com.r4j35h.trendingmovies.base.BaseRepo
import com.r4j35h.trendingmovies.ui.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(ApiServiceModule::class))
@Singleton
interface ApplicationComponent {

    fun inject(baseActivity: BaseActivity)
    fun inject(application: TrendingMoviesApp)
    fun inject(baseRepo: BaseRepo)
    fun inject(viewModel: MainActivityViewModel)


}