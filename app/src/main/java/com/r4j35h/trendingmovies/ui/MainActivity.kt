package com.r4j35h.trendingmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.r4j35h.trendingmovies.R
import com.r4j35h.trendingmovies.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}