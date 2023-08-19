package com.damai.domain.usecases

import com.damai.base.networks.FlowUseCase
import com.damai.base.networks.Resource
import com.damai.domain.models.MovieDetailsModel
import com.damai.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai007 on 19/August/2023
 */
class GetMovieDetailsUseCase(
    private val homeRepository: HomeRepository
) : FlowUseCase<Int, MovieDetailsModel>() {

    override suspend fun execute(parameters: Int?): Flow<Resource<MovieDetailsModel>> {
        return homeRepository.getMovieDetails(movieId = requireNotNull(parameters))
    }
}