package com.damai.accordinnovations.ui.detail

import com.damai.accordinnovations.R
import com.damai.accordinnovations.databinding.ActivityMovieDetailBinding
import com.damai.base.BaseActivity
import com.damai.base.extensions.setCustomOnClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by damai007 on 19/August/2023
 */
class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding, MovieDetailViewModel>() {

    override val layoutResource: Int = R.layout.activity_movie_detail

    override val viewModel: MovieDetailViewModel by viewModel()

    override fun ActivityMovieDetailBinding.viewInitialization() {
        viewModel.initFromIntent(bundle = intent.extras)
        vm = viewModel
        lifecycleOwner = this@MovieDetailActivity
    }

    override fun ActivityMovieDetailBinding.setupListeners() {
        clMovieReviewCount.setCustomOnClickListener {
            // TODO: Open bottom sheet dialog to show user's reviews
        }
    }

    override fun ActivityMovieDetailBinding.onPreparationFinished() {
        viewModel.getMovieDetails()
    }
}