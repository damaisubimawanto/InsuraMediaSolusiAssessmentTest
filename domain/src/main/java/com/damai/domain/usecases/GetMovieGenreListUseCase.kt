package com.damai.domain.usecases

import com.damai.base.networks.FlowUseCase
import com.damai.base.networks.Resource
import com.damai.domain.models.MovieGenreListModel
import com.damai.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai007 on 18/August/2023
 */
class GetMovieGenreListUseCase(
    private val homeRepository: HomeRepository
) : FlowUseCase<Nothing, MovieGenreListModel>() {

    override suspend fun execute(parameters: Nothing?): Flow<Resource<MovieGenreListModel>> {
        return homeRepository.getMovieGenreList()
    }
}