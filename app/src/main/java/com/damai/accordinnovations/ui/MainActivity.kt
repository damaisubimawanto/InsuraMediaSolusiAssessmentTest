package com.damai.accordinnovations.ui

import com.damai.accordinnovations.R
import com.damai.accordinnovations.databinding.ActivityMainBinding
import com.damai.accordinnovations.ui.main.adapters.GenreAdapter
import com.damai.base.BaseActivity
import com.damai.base.extensions.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    //region Variables
    private lateinit var mGenreAdapter: GenreAdapter
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

        }
    }

    override fun ActivityMainBinding.setupObservers() {
        observe(viewModel.genreListLiveData) {
            mGenreAdapter.setNewData(newData = it)
        }
    }

    override fun ActivityMainBinding.onPreparationFinished() {
        viewModel.getGenreList()
    }
}