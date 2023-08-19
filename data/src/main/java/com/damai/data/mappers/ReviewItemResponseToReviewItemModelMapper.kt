package com.damai.data.mappers

import com.damai.base.BaseMapper
import com.damai.data.responses.ReviewItemResponse
import com.damai.domain.models.ReviewItemModel

/**
 * Created by damai007 on 19/August/2023
 */
class ReviewItemResponseToReviewItemModelMapper : BaseMapper<ReviewItemResponse, ReviewItemModel>() {

    override fun map(value: ReviewItemResponse): ReviewItemModel {
        return ReviewItemModel(
            id = value.id,
            authorName = value.author,
            content = value.content
        )
    }
}