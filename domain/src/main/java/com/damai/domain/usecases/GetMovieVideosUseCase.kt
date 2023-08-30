package com.damai.domain.usecases

import com.damai.base.networks.FlowUseCase
import com.damai.base.networks.Resource
import com.damai.domain.models.MovieVideoListModel
import com.damai.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai007 on 30/August/2023
 */
class GetMovieVideosUseCase(
    private val homeRepository: HomeRepository
) : FlowUseCase<Int, MovieVideoListModel>() {

    override suspend fun execute(parameters: Int?): Flow<Resource<MovieVideoListModel>> {
        return homeRepository.getMovieVideos(movieId = requireNotNull(parameters))
    }
}