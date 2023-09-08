package com.damai.mandiribank.ui.review.adapters

import androidx.recyclerview.widget.DiffUtil
import com.damai.domain.models.ReviewUiModel

/**
 * Created by damai007 on 19/August/2023
 */
object ReviewUiComparator : DiffUtil.ItemCallback<ReviewUiModel>() {

    override fun areItemsTheSame(oldItem: ReviewUiModel, newItem: ReviewUiModel): Boolean {
        val isReviewItem = oldItem is ReviewUiModel.ReviewItemWrap &&
                newItem is ReviewUiModel.ReviewItemWrap &&
                oldItem.data.id == newItem.data.id

        val isLoadingFooter = oldItem is ReviewUiModel.ReviewFooterLoading &&
                newItem is ReviewUiModel.ReviewFooterLoading

        return (isReviewItem || isLoadingFooter)
    }

    override fun areContentsTheSame(oldItem: ReviewUiModel, newItem: ReviewUiModel): Boolean {
        return oldItem == newItem
    }
}