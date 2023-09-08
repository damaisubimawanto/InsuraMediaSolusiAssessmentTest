package com.damai.mandiribank.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.damai.mandiribank.R
import com.damai.mandiribank.databinding.RecyclerItemGenreBinding
import com.damai.base.BaseRecyclerViewAdapter
import com.damai.base.BaseViewHolder
import com.damai.base.extensions.setCustomOnClickListener
import com.damai.domain.models.MovieGenreModel

/**
 * Created by damai007 on 18/August/2023
 */
class GenreAdapter(
    private val clickCallback: (MovieGenreModel) -> Unit
) : BaseRecyclerViewAdapter<RecyclerItemGenreBinding, MovieGenreModel>(
    data = listOf()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<RecyclerItemGenreBinding, MovieGenreModel> {
        val binding = DataBindingUtil.inflate<RecyclerItemGenreBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recycler_item_genre,
            parent,
            false
        )
        return GenreViewHolder(dataBinding = binding)
    }

    inner class GenreViewHolder(
        dataBinding: RecyclerItemGenreBinding
    ) : BaseViewHolder<RecyclerItemGenreBinding, MovieGenreModel>(binding = dataBinding) {

        override fun bind(data: MovieGenreModel) {
            with(binding) {
                model = data
                if (hasPendingBindings()) {
                    executePendingBindings()
                }

                tvGenreName.setCustomOnClickListener {
                    clickCallback.invoke(data)
                }
            }
        }
    }
}