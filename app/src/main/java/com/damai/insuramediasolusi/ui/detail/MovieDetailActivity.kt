package com.damai.insuramediasolusi.ui.detail

import com.damai.insuramediasolusi.R
import com.damai.insuramediasolusi.databinding.ActivityMovieDetailBinding
import com.damai.insuramediasolusi.ui.review.MovieReviewsBottomSheetDialog
import com.damai.base.BaseActivity
import com.damai.base.extensions.observe
import com.damai.base.extensions.orZero
import com.damai.base.extensions.setCustomOnClickListener
import com.damai.base.extensions.setCustomTextColor
import com.damai.base.extensions.setCustomTint
import com.damai.base.extensions.showShortToast
import com.damai.base.utils.IntentUtil
import com.damai.base.utils.VideoPlatformType
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
            if (viewModel.movieReviewsCountLiveData.value.orZero() == 0) {
                showShortToast(message = getString(R.string.empty_review_message))
                return@setCustomOnClickListener
            }
            val fragment = MovieReviewsBottomSheetDialog()
            fragment.show(
                supportFragmentManager,
                MovieReviewsBottomSheetDialog.TAG
            )
        }
    }

    override fun ActivityMovieDetailBinding.setupObservers() {
        observe(viewModel.trailerMovieLiveData) { trailerMovie ->
            if (trailerMovie == null || trailerMovie.key.isNullOrEmpty()) {
                ivTrailerMovieVideo.setCustomTint(colorRes = R.color.dim_gray)
                tvTrailerMovieVideo.setCustomTextColor(colorRes = R.color.dim_gray)
                clTrailerMovieVideo.setOnClickListener(null)
            } else {
                ivTrailerMovieVideo.setCustomTint(colorRes = R.color.yellow)
                tvTrailerMovieVideo.setCustomTextColor(colorRes = R.color.white)

                clTrailerMovieVideo.setCustomOnClickListener {
                    when (trailerMovie.site) {
                        VideoPlatformType.Youtube.type -> {
                            IntentUtil.openYoutubeApp(
                                context = this@MovieDetailActivity,
                                youtubeId = requireNotNull(trailerMovie.key)
                            )
                        }
                        VideoPlatformType.Vimeo.type -> {
                            IntentUtil.openVimeoApp(
                                context = this@MovieDetailActivity,
                                vimeoId = requireNotNull(trailerMovie.key)
                            )
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    override fun ActivityMovieDetailBinding.onPreparationFinished() {
        viewModel.getMovieDetails()
        viewModel.getMovieReviewsCount()
        viewModel.getMovieVideos()
    }

    override fun isRemoveFitsSystemWindows(): Boolean = true
}