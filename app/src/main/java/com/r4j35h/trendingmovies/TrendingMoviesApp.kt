package com.r4j35h.trendingmovies

import android.app.Application
import android.util.Log
import androidx.annotation.NonNull
import com.r4j35h.trendingmovies.di.ApiServiceModule
import com.r4j35h.trendingmovies.di.ApplicationComponent
import com.r4j35h.trendingmovies.di.DaggerApplicationComponent
import com.r4j35h.trendingmovies.network.ApiService
import timber.log.Timber
import javax.inject.Inject

class TrendingMoviesApp: Application() {
    val component: ApplicationComponent? by lazy {
        DaggerApplicationComponent.builder().apiServiceModule(ApiServiceModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        component?.inject(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }


    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, @NonNull message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }
            Timber.log(priority, tag, t)

            if (t != null) {
                if (priority == Log.ERROR) {
                    Timber.log(priority, t)
                } else if (priority == Log.WARN) {
                    Timber.log(priority, t)
                }
            }
        }
    }
}