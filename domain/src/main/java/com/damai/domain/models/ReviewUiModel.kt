package com.damai.domain.models

/**
 * Created by damai007 on 19/August/2023
 */
sealed class ReviewUiModel {

    data class ReviewItemWrap(val data: ReviewItemModel) : ReviewUiModel()

    object ReviewFooterLoading : ReviewUiModel()
}
