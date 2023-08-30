package com.damai.insuramediasolusi.ui.detail

import com.damai.insuramediasolusi.R
import com.damai.insuramediasolusi.databinding.ActivityMovieDetailBinding
import com.damai.insuramediasolusi.ui.review.MovieReviewsBottomSheetDialog
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
            val fragment = MovieReviewsBottomSheetDialog()
            fragment.show(
                supportFragmentManager,
                MovieReviewsBottomSheetDialog.TAG
            )
        }
    }

    override fun ActivityMovieDetailBinding.onPreparationFinished() {
        viewModel.getMovieDetails()
    }

    override fun isRemoveFitsSystemWindows(): Boolean = true
}