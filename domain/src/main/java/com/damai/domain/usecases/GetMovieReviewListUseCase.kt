package com.damai.domain.usecases

import com.damai.base.networks.FlowUseCase
import com.damai.base.networks.Resource
import com.damai.domain.models.ReviewItemListModel
import com.damai.domain.models.ReviewItemRequestModel
import com.damai.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai007 on 19/August/2023
 */
class GetMovieReviewListUseCase(
    private val homeRepository: HomeRepository
) : FlowUseCase<ReviewItemRequestModel, ReviewItemListModel>() {

    override suspend fun execute(parameters: ReviewItemRequestModel?): Flow<Resource<ReviewItemListModel>> {
        return homeRepository.getMovieReviewList(requestModel = requireNotNull(parameters))
    }
}