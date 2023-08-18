package com.damai.accordinnovations.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.damai.accordinnovations.R
import com.damai.accordinnovations.databinding.RecyclerItemGenreBinding
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
                tvGenreName.text = data.name

                tvGenreName.setCustomOnClickListener {
                    clickCallback.invoke(data)
                }
            }
        }
    }
}