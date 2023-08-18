package com.damai.accordinnovations.ui

import androidx.recyclerview.widget.GridLayoutManager
import com.damai.accordinnovations.R
import com.damai.accordinnovations.databinding.ActivityMainBinding
import com.damai.accordinnovations.ui.main.adapters.GenreAdapter
import com.damai.accordinnovations.ui.main.adapters.MovieAdapter
import com.damai.accordinnovations.ui.main.adapters.MovieAdapter.Companion.FOOTER_ITEM
import com.damai.base.BaseActivity
import com.damai.base.extensions.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    //region Variables
    private lateinit var mGenreAdapter: GenreAdapter
    private lateinit var mMovieAdapter: MovieAdapter
    //endregion `Variables`

    override val layoutResource: Int = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun ActivityMainBinding.viewInitialization() {
        with(rvGenres) {
            mGenreAdapter = GenreAdapter {
                // TODO: Handle click on genre tab item
            }
            adapter = mGenreAdapter
        }

        with(rvMovies) {
            mMovieAdapter = MovieAdapter {
                // TODO: Handle click on movie item
            }
            val spanCount = 3
            val gridLayoutManager = GridLayoutManager(this@MainActivity, spanCount).also {
                it.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (mMovieAdapter.getItemViewType(position)) {
                            FOOTER_ITEM -> spanCount
                            else -> 1
                        }
                    }
                }
            }
            layoutManager = gridLayoutManager
            adapter = mMovieAdapter
        }
    }

    override fun ActivityMainBinding.setupObservers() {
        observe(viewModel.genreListLiveData) {
            mGenreAdapter.setNewData(newData = it)
        }

        observe(viewModel.movieListLiveData) {
            mMovieAdapter.submitList(it)
        }
    }

    override fun ActivityMainBinding.onPreparationFinished() {
        viewModel.getGenreList()
    }
}