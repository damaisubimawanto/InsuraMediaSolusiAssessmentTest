package com.damai.insuramediasolusi.ui.main

import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.damai.insuramediasolusi.R
import com.damai.insuramediasolusi.databinding.ActivityMainBinding
import com.damai.insuramediasolusi.navigations.PageNavigationApi
import com.damai.insuramediasolusi.ui.main.adapters.GenreAdapter
import com.damai.insuramediasolusi.ui.main.adapters.MovieAdapter
import com.damai.insuramediasolusi.ui.main.adapters.MovieAdapter.Companion.FOOTER_ITEM
import com.damai.base.BaseActivity
import com.damai.base.extensions.observe
import com.damai.base.utils.EndlessScrollListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    //region Variables
    private lateinit var mGenreAdapter: GenreAdapter
    private lateinit var mMovieAdapter: MovieAdapter

    private val pageNavigationApi: PageNavigationApi by inject()

    private val activityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            /* Do nothing. */
        }

    private val mEndlessScrollListener: EndlessScrollListener by lazy {
        object : EndlessScrollListener(
            layoutManager = requireNotNull(binding.rvMovies.layoutManager),
            visibleThreshold = 3
        ) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.changeCurrentPage(newPage = page)
                viewModel.getMovieList()
            }
        }
    }
    //endregion `Variables`

    override val layoutResource: Int = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun ActivityMainBinding.viewInitialization() {
        with(rvGenres) {
            mGenreAdapter = GenreAdapter { genreModel ->
                viewModel.changeGenre(genreId = genreModel.id) { selectedPosition ->
                    mEndlessScrollListener.resetScrolling()
                    rvGenres.smoothScrollToPosition(selectedPosition)
                }
            }
            adapter = mGenreAdapter
        }

        with(rvMovies) {
            mMovieAdapter = MovieAdapter {
                pageNavigationApi.navigateToMovieDetailActivity(
                    context = this@MainActivity,
                    launcher = activityLauncher,
                    movieId = it.id
                )
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
            clearOnScrollListeners()
            addOnScrollListener(mEndlessScrollListener)
        }
    }

    override fun ActivityMainBinding.setupObservers() {
        observe(viewModel.genreListLiveData) {
            mGenreAdapter.setNewData(newData = it)
        }

        observe(viewModel.movieListLiveData) {
            if (viewModel.isMovieListReset) {
                viewModel.isMovieListReset = false
                mMovieAdapter.submitList(null)
            }
            mMovieAdapter.submitList(it)
        }
    }

    override fun ActivityMainBinding.onPreparationFinished() {
        viewModel.getGenreList()
    }
}