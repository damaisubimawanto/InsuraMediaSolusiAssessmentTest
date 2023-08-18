package com.damai.data.mappers

import com.damai.base.BaseMapper
import com.damai.base.extensions.orZero
import com.damai.data.responses.MovieGenreResponse
import com.damai.domain.models.MovieGenreModel

/**
 * Created by damai007 on 18/August/2023
 */
class MovieGenreResponseToMovieGenreModelMapper : BaseMapper<MovieGenreResponse, MovieGenreModel>() {

    override fun map(value: MovieGenreResponse): MovieGenreModel {
        return MovieGenreModel(
            id = value.id.orZero(),
            name = value.name.orEmpty()
        )
    }
}