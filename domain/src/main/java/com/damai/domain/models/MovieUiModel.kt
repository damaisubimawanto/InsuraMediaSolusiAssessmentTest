package com.damai.domain.models

/**
 * Created by damai007 on 19/August/2023
 */
sealed class MovieUiModel {

    data class MovieItemWrap(val data: MovieItemModel) : MovieUiModel()

    object MovieFooterLoading : MovieUiModel()
}
