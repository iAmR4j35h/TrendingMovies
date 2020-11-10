package com.r4j35h.trendingmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.r4j35h.trendingmovies.R
import com.r4j35h.trendingmovies.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    lateinit var adapter: MoviesAdapter
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        adapter = MoviesAdapter()
        gridLayoutManager=GridLayoutManager(this,2)
        movies_recycler.layoutManager=gridLayoutManager
        movies_recycler.adapter=adapter
        viewModel.getMovies().observe(this, Observer {

            if (it != null) {
                viewModel.moviesList.addAll(it.movieModels)
                viewModel.page=it.page
                adapter.submitList(viewModel.moviesList)
                if(!viewModel.moviesList.isNullOrEmpty()){
                    progress_bar.visibility=ProgressBar.GONE
                }
            }
            viewModel.isDataLoading=false
        })

        viewModel.fetchMovies()
        setUpPagination()
    }

    private fun setUpPagination() {
        movies_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = gridLayoutManager.itemCount;
                val lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                if (!viewModel.isDataLoading && totalItemCount <= (lastVisibleItem + 40)) {
                    viewModel.isDataLoading = true
                    progress_bar.visibility=ProgressBar.VISIBLE
                    viewModel.fetchMovies()
                }
            }

        })
    }


}