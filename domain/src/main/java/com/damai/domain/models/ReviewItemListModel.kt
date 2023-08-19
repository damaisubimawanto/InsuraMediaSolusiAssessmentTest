package com.damai.domain.models

import com.damai.base.BaseModel

/**
 * Created by damai007 on 19/August/2023
 */
data class ReviewItemListModel(
    val list: List<ReviewItemModel>? = null
) : BaseModel()

data class ReviewItemModel(
    val id: String?,
    val authorName: String?,
    val content: String?
)