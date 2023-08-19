package com.damai.accordinnovations.ui.review

import androidx.recyclerview.widget.RecyclerView
import com.damai.accordinnovations.R
import com.damai.accordinnovations.databinding.FragmentMovieReviewsBinding
import com.damai.accordinnovations.ui.detail.MovieDetailViewModel
import com.damai.accordinnovations.ui.review.adapters.ReviewAdapter
import com.damai.base.BaseBottomSheetDialogFragment
import com.damai.base.extensions.getScreenHeight
import com.damai.base.extensions.observe
import com.damai.base.utils.EndlessScrollListener
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
        setBottomSheetFullScreen(getScreenHeight(), binding.root)
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
    }

    override fun FragmentMovieReviewsBinding.onPreparationFinished() {
        viewModel.getMovieReviews()
    }

    companion object {
        const val TAG = "MovieReviewsBottomSheetDialog"
    }
}