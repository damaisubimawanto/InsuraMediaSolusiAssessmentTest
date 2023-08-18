package com.damai.data.repos

import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.networks.NetworkResource
import com.damai.base.networks.Resource
import com.damai.base.utils.Constants.QUERY_LANGUAGE_DEFAULT
import com.damai.base.utils.Constants.SUCCESS_CODE
import com.damai.data.apiservices.HomeService
import com.damai.data.mappers.MovieGenreResponseToMovieGenreModelMapper
import com.damai.domain.models.MovieGenreListModel
import com.damai.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai007 on 18/August/2023
 */
class HomeRepositoryImpl(
    private val homeService: HomeService,
    private val dispatcher: DispatcherProvider,
    private val movieGenreMapper: MovieGenreResponseToMovieGenreModelMapper
) : HomeRepository {

    override fun getMovieGenreList(): Flow<Resource<MovieGenreListModel>> {
        return object : NetworkResource<MovieGenreListModel>(
            dispatcherProvider = dispatcher
        ) {
            override suspend fun remoteFetch(): MovieGenreListModel {
                val response = homeService.getMovieGenreList(
                    language = QUERY_LANGUAGE_DEFAULT
                )
                return MovieGenreListModel(
                    list = response.genres?.map {
                        movieGenreMapper.map(it)
                    }
                ).also {
                    it.status = SUCCESS_CODE
                }
            }
        }.asFlow()
    }
}