package com.r4j35h.trendingmovies.ui

import android.graphics.Paint
import android.os.Bundle
import android.widget.TextView
import com.google.gson.Gson
import com.r4j35h.trendingmovies.R
import com.r4j35h.trendingmovies.base.BaseActivity
import com.r4j35h.trendingmovies.network.ApiConstants
import com.r4j35h.trendingmovies.network.model.MovieModelResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.item_movie_card.view.*

class MovieDetailActivity : BaseActivity() {
    lateinit var movieData:MovieModelResponse.MovieModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        intent.extras.let {
            movieData=Gson().fromJson(it?.getString("movie"),MovieModelResponse.MovieModel::class.java)
        }


        setData(movieData)

    }

    private fun setData(data: MovieModelResponse.MovieModel) {
        Picasso.get().load(ApiConstants.IMG_SMALL_BASE_URL+data.backdropPath).into(banner_img)
        Picasso.get().load(ApiConstants.IMG_SMALL_BASE_URL+data.posterPath).into(poster_iv)
        title_id.let {
            if(data.title!=null)
                it.setText(data.title)
            else  if(data.originalTitle!=null)
                it.setText(data.originalTitle)
            else
                it.setText(data.name)
        }
//        title_id.setText(data.title)
        date_tv.setText(data.releaseDate)
        lng_tv.setText(data.originalLanguage)
        desc_tv.setText(data.overview)
        content_r_tv.setText("18+")
        if(data.adult!=null&&!data.adult)
        content_r_tv.setPaintFlags(content_r_tv.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)


        rating_tv.setText((data.voteAverage*10).toInt().toString()+"%")
        progress_id.progress=(data.voteAverage*10).toInt()

    }
}