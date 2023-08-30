package com.damai.domain.models

import com.damai.base.BaseModel

/**
 * Created by damai007 on 30/August/2023
 */
data class MovieVideoListModel(
    val list: List<MovieVideoModel>? = null
) : BaseModel()

data class MovieVideoModel(
    val id: String?,
    val site: String?,
    val key: String?,
    val type: String?
)
