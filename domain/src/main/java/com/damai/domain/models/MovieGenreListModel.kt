package com.damai.domain.models

import com.damai.base.BaseModel

/**
 * Created by damai007 on 18/August/2023
 */
data class MovieGenreListModel(
    val list: List<MovieGenreModel>? = null
) : BaseModel()

data class MovieGenreModel(
    val id: Int,
    val name: String,
    var isSelected: Boolean
)
