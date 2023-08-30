package com.damai.insuramediasolusi.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.damai.insuramediasolusi.R
import com.damai.insuramediasolusi.databinding.RecyclerItemMovieBinding
import com.damai.insuramediasolusi.databinding.RecyclerItemMovieFooterBinding
import com.damai.base.BaseViewHolder
import com.damai.base.extensions.setCustomOnClickListener
import com.damai.domain.models.MovieItemModel
import com.damai.domain.models.MovieUiModel

/**
 * Created by damai007 on 19/August/2023
 */
class MovieAdapter(
    private val clickCallback: (MovieItemModel) -> Unit
) : ListAdapter<MovieUiModel, RecyclerView.ViewHolder>(MovieUiComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FOOTER_ITEM -> {
                val binding = DataBindingUtil.inflate<RecyclerItemMovieFooterBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.recycler_item_movie_footer,
                    parent,
                    false
                )
                MovieFooterViewHolder(dataBinding = binding)
            }
            else -> {
                val binding = DataBindingUtil.inflate<RecyclerItemMovieBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.recycler_item_movie,
                    parent,
                    false
                )
                MovieViewHolder(dataBinding = binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieFooterViewHolder -> {
                when (val data = getItem(position)) {
                    is MovieUiModel.MovieFooterLoading -> holder.bind(data = data)
                    else -> Unit
                }
            }

            is MovieViewHolder -> {
                when (val data = getItem(position)) {
                    is MovieUiModel.MovieItemWrap -> holder.bind(data = data.data)
                    else -> Unit
                }
            }

            else -> Unit
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MovieUiModel.MovieItemWrap -> MOVIE_ITEM
            is MovieUiModel.MovieFooterLoading -> FOOTER_ITEM
        }
    }

    inner class MovieViewHolder(
        dataBinding: RecyclerItemMovieBinding
    ) : BaseViewHolder<RecyclerItemMovieBinding, MovieItemModel>(binding = dataBinding) {

        override fun bind(data: MovieItemModel) {
            with(binding) {
                model = data
                if (hasPendingBindings()) {
                    executePendingBindings()
                }

                clMainView.setCustomOnClickListener {
                    clickCallback.invoke(data)
                }
            }
        }
    }

    inner class MovieFooterViewHolder(
        dataBinding: RecyclerItemMovieFooterBinding
    ) : BaseViewHolder<RecyclerItemMovieFooterBinding, MovieUiModel.MovieFooterLoading>(
        binding = dataBinding
    ) {

        override fun bind(data: MovieUiModel.MovieFooterLoading) {
            /* Do nothing. */
        }
    }

    companion object {
        private const val MOVIE_ITEM = 0
        const val FOOTER_ITEM = 1
    }
}