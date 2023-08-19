package com.damai.domain.models

import com.damai.base.BaseModel

/**
 * Created by damai007 on 18/August/2023
 */
data class MovieItemListModel(
    val list: List<MovieItemModel>? = null
) : BaseModel()

data class MovieItemModel(
    val id: Int,
    val isAdult: Boolean,
    val originalTitle: String?,
    val title: String?,
    val overview: String?,
    val bannerPath: String?,
    val posterPath: String?,
    val voteAverage: Double,
    val runtime: Int,
    val releaseDate: String?,
    val genresText: String?
)
