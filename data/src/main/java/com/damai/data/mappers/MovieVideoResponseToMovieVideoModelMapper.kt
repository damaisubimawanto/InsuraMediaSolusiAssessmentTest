package com.damai.data.mappers

import com.damai.base.BaseMapper
import com.damai.data.responses.MovieVideoResponse
import com.damai.domain.models.MovieVideoModel

/**
 * Created by damai007 on 30/August/2023
 */
class MovieVideoResponseToMovieVideoModelMapper : BaseMapper<MovieVideoResponse, MovieVideoModel>() {

    override fun map(value: MovieVideoResponse): MovieVideoModel {
        return MovieVideoModel(
            id = value.id,
            site = value.site,
            key = value.key,
            type = value.type
        )
    }
}