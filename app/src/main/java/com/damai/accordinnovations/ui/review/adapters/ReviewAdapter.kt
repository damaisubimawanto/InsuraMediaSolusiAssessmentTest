package com.damai.accordinnovations.ui.review.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.damai.accordinnovations.R
import com.damai.accordinnovations.databinding.RecyclerItemMovieFooterBinding
import com.damai.accordinnovations.databinding.RecyclerItemReviewBinding
import com.damai.base.BaseViewHolder
import com.damai.domain.models.ReviewItemModel
import com.damai.domain.models.ReviewUiModel

/**
 * Created by damai007 on 19/August/2023
 */
class ReviewAdapter : ListAdapter<ReviewUiModel, RecyclerView.ViewHolder>(
    ReviewUiComparator
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FOOTER_ITEM -> {
                val binding = DataBindingUtil.inflate<RecyclerItemMovieFooterBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.recycler_item_movie_footer,
                    parent,
                    false
                )
                ReviewFooterViewHolder(dataBinding = binding)
            }
            else -> {
                val binding = DataBindingUtil.inflate<RecyclerItemReviewBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.recycler_item_review,
                    parent,
                    false
                )
                ReviewViewHolder(dataBinding = binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ReviewFooterViewHolder -> {
                when (val data = getItem(position)) {
                    is ReviewUiModel.ReviewFooterLoading -> holder.bind(data = data)
                    else -> Unit
                }
            }

            is ReviewViewHolder -> {
                when (val data = getItem(position)) {
                    is ReviewUiModel.ReviewItemWrap -> holder.bind(data = data.data)
                    else -> Unit
                }
            }

            else -> Unit
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ReviewUiModel.ReviewItemWrap -> REVIEW_ITEM
            is ReviewUiModel.ReviewFooterLoading -> FOOTER_ITEM
        }
    }

    inner class ReviewViewHolder(
        dataBinding: RecyclerItemReviewBinding
    ) : BaseViewHolder<RecyclerItemReviewBinding, ReviewItemModel>(binding = dataBinding) {

        override fun bind(data: ReviewItemModel) {
            with(binding) {
                model = data
                if (hasPendingBindings()) {
                    executePendingBindings()
                }
            }
        }
    }

    inner class ReviewFooterViewHolder(
        dataBinding: RecyclerItemMovieFooterBinding
    ) : BaseViewHolder<RecyclerItemMovieFooterBinding, ReviewUiModel.ReviewFooterLoading>(
        binding = dataBinding
    ) {

        override fun bind(data: ReviewUiModel.ReviewFooterLoading) {
            /* Do nothing. */
        }
    }

    companion object {
        private const val REVIEW_ITEM = 0
        const val FOOTER_ITEM = 1
    }
}