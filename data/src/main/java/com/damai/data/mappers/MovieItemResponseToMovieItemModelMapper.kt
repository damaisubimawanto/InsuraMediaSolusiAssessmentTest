package com.damai.data.mappers

import com.damai.base.BaseMapper
import com.damai.base.extensions.orFalse
import com.damai.base.extensions.orZero
import com.damai.data.responses.MovieItemResponse
import com.damai.domain.models.MovieItemModel

/**
 * Created by damai007 on 18/August/2023
 */
class MovieItemResponseToMovieItemModelMapper : BaseMapper<MovieItemResponse, MovieItemModel>() {

    override fun map(value: MovieItemResponse): MovieItemModel {
        return MovieItemModel(
            id = value.id.orZero(),
            isAdult = value.adult.orFalse(),
            originalTitle = value.originalTitle,
            title = value.title,
            overview = value.overview,
            posterPath = value.posterPath,
            voteAverage = value.voteAverage.orZero(),
            releaseDate = value.releaseDate
        )
    }
}