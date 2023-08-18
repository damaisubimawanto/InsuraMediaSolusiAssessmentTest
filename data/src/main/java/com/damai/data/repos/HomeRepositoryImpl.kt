package com.damai.data.repos

import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.networks.NetworkResource
import com.damai.base.networks.Resource
import com.damai.base.utils.Constants.QUERY_LANGUAGE_DEFAULT
import com.damai.base.utils.Constants.QUERY_LANGUAGE_US_DEFAULT
import com.damai.base.utils.Constants.QUERY_SORT_BY_DEFAULT
import com.damai.base.utils.Constants.SUCCESS_CODE
import com.damai.data.apiservices.HomeService
import com.damai.data.mappers.MovieGenreResponseToMovieGenreModelMapper
import com.damai.data.mappers.MovieItemResponseToMovieItemModelMapper
import com.damai.domain.models.MovieGenreListModel
import com.damai.domain.models.MovieItemByGenreRequestModel
import com.damai.domain.models.MovieItemListModel
import com.damai.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai007 on 18/August/2023
 */
class HomeRepositoryImpl(
    private val homeService: HomeService,
    private val dispatcher: DispatcherProvider,
    private val movieGenreMapper: MovieGenreResponseToMovieGenreModelMapper,
    private val movieItemMapper: MovieItemResponseToMovieItemModelMapper
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

    override fun getMovieItemListByGenre(
        requestModel: MovieItemByGenreRequestModel
    ): Flow<Resource<MovieItemListModel>> {
        return object : NetworkResource<MovieItemListModel>(
            dispatcherProvider = dispatcher
        ) {
            override suspend fun remoteFetch(): MovieItemListModel {
                val response = homeService.getMoviePaginatedListByGenre(
                    withGenres = requestModel.genreName,
                    sortBy = QUERY_SORT_BY_DEFAULT,
                    language = QUERY_LANGUAGE_US_DEFAULT,
                    includeAdult = false,
                    includeVideo = false,
                    page = requestModel.page
                )
                return MovieItemListModel(
                    list = response.results?.map {
                        movieItemMapper.map(it)
                    }
                ).also {
                    it.status = SUCCESS_CODE
                }
            }
        }.asFlow()
    }
}