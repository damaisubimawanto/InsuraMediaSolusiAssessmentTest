package com.damai.mandiribank.ui.main.adapters

import androidx.recyclerview.widget.DiffUtil
import com.damai.domain.models.MovieUiModel

/**
 * Created by damai007 on 19/August/2023
 */
object MovieUiComparator : DiffUtil.ItemCallback<MovieUiModel>() {

    override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        val isMovieItem = oldItem is MovieUiModel.MovieItemWrap &&
                newItem is MovieUiModel.MovieItemWrap &&
                oldItem.data.id == newItem.data.id

        val isLoadingFooter = oldItem is MovieUiModel.MovieFooterLoading &&
                newItem is MovieUiModel.MovieFooterLoading

        return (isMovieItem || isLoadingFooter)
    }

    override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem == newItem
    }
}