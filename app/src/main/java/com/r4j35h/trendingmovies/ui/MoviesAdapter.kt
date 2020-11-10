package com.r4j35h.trendingmovies.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.r4j35h.trendingmovies.network.model.MovieModelResponse
import com.r4j35h.trendingmovies.R
import com.r4j35h.trendingmovies.network.ApiConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_card.view.*

class MoviesAdapter :ListAdapter<MovieModelResponse.MovieModel,MoviesAdapter.MoviesViewHolder>(MoviesDiffutils()){


    inner class MoviesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        fun bind(position: Int){
            val item=getItem(position)
            Picasso.get().load(ApiConstants.IMG_SMALL_BASE_URL+item.posterPath).into(itemView.movie_img)
            itemView.title_tv.let {
                if(item.originalTitle!=null)
                it.setText(item.originalTitle)
                else  if(item.name!=null)
                    it.setText(item.name)
                else
                    it.setText(item.title)
            }
            itemView.rating_tv.setText((item.voteAverage*10).toInt().toString()+"%")
            itemView.progress_id.progress=(item.voteAverage*10).toInt()


            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context,MovieDetailActivity::class.java).putExtra("movie",Gson().toJson(item)))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_card,parent,false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(position)
    }


     class MoviesDiffutils:DiffUtil.ItemCallback<MovieModelResponse.MovieModel>(){
        override fun areItemsTheSame(
            oldItem: MovieModelResponse.MovieModel,
            newItem: MovieModelResponse.MovieModel
        ): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieModelResponse.MovieModel,
            newItem: MovieModelResponse.MovieModel
        ): Boolean {
            return Gson().toJson(oldItem).equals(Gson().toJson(newItem))
        }

    }
}