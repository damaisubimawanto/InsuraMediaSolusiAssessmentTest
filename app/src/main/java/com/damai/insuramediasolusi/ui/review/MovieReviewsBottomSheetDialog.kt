package com.damai.insuramediasolusi.ui.review

import androidx.recyclerview.widget.RecyclerView
import com.damai.insuramediasolusi.R
import com.damai.insuramediasolusi.databinding.FragmentMovieReviewsBinding
import com.damai.insuramediasolusi.ui.detail.MovieDetailViewModel
import com.damai.insuramediasolusi.ui.review.adapters.ReviewAdapter
import com.damai.base.BaseBottomSheetDialogFragment
import com.damai.base.extensions.getScreenHeight
import com.damai.base.extensions.observe
import com.damai.base.extensions.showShortToast
import com.damai.base.utils.EndlessScrollListener
import com.damai.base.utils.EventObserver
import org.koin.androidx.viewmodel.ext.android.activityViewModel

/**
 * Created by damai007 on 19/August/2023
 */
class MovieReviewsBottomSheetDialog : BaseBottomSheetDialogFragment<FragmentMovieReviewsBinding, MovieDetailViewModel>() {

    //region Variables
    private lateinit var mReviewAdapter: ReviewAdapter

    private val mEndlessScrollListener: EndlessScrollListener by lazy {
        object : EndlessScrollListener(
            layoutManager = requireNotNull(binding.rvReviews.layoutManager),
            visibleThreshold = 5
        ) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (viewModel.isStopLoadMore) return
                viewModel.changeReviewCurrentPage(newPage = page)
                viewModel.getMovieReviews()
            }
        }
    }
    //endregion `Variables`

    override val layoutResource: Int = R.layout.fragment_movie_reviews

    override val viewModel: MovieDetailViewModel by activityViewModel()

    override fun onStart() {
        super.onStart()
        setBottomSheetFullScreen(
            height = getScreenHeight(),
            bottomSheet = binding.root,
            skipCollapsed = true
        )
    }

    override fun FragmentMovieReviewsBinding.viewInitialization() {
        viewModel.resetReviewList()
        with(rvReviews) {
            mReviewAdapter = ReviewAdapter()
            adapter = mReviewAdapter
            clearOnScrollListeners()
            addOnScrollListener(mEndlessScrollListener)
        }
    }

    override fun FragmentMovieReviewsBinding.setupObservers() {
        observe(viewModel.movieReviewsLiveData) {
            mReviewAdapter.submitList(it)
        }

        observe(viewModel.errorMovieReviewsLiveData, EventObserver { errorPair ->
            if (errorPair.first) {
                val message = if (errorPair.second.isNullOrBlank()) {
                    getString(R.string.error_movie_reviews)
                } else {
                    requireNotNull(errorPair.second)
                }
                context?.showShortToast(message = message)
            }
        })
    }

    override fun FragmentMovieReviewsBinding.onPreparationFinished() {
        viewModel.getMovieReviews()
    }

    companion object {
        const val TAG = "MovieReviewsBottomSheetDialog"
    }
}