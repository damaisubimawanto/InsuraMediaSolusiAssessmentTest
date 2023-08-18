package com.damai.domain.usecases

import com.damai.base.networks.FlowUseCase
import com.damai.base.networks.Resource
import com.damai.domain.models.MovieItemByGenreRequestModel
import com.damai.domain.models.MovieItemListModel
import com.damai.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai007 on 18/August/2023
 */
class GetMovieItemListUseCase(
    private val homeRepository: HomeRepository
) : FlowUseCase<MovieItemByGenreRequestModel, MovieItemListModel>() {

    override suspend fun execute(parameters: MovieItemByGenreRequestModel?): Flow<Resource<MovieItemListModel>> {
        return homeRepository.getMovieItemListByGenre(requestModel = requireNotNull(parameters))
    }
}