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
import com.damai.data.mappers.MovieVideoResponseToMovieVideoModelMapper
import com.damai.data.mappers.ReviewItemResponseToReviewItemModelMapper
import com.damai.domain.models.MovieDetailsModel
import com.damai.domain.models.MovieGenreListModel
import com.damai.domain.models.MovieItemByGenreRequestModel
import com.damai.domain.models.MovieItemListModel
import com.damai.domain.models.MovieVideoListModel
import com.damai.domain.models.ReviewItemListModel
import com.damai.domain.models.ReviewItemRequestModel
import com.damai.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai007 on 18/August/2023
 */
class HomeRepositoryImpl(
    private val homeService: HomeService,
    private val dispatcher: DispatcherProvider,
    private val movieGenreMapper: MovieGenreResponseToMovieGenreModelMapper,
    private val movieItemMapper: MovieItemResponseToMovieItemModelMapper,
    private val movieVideoMapper: MovieVideoResponseToMovieVideoModelMapper,
    private val reviewItemMapper: ReviewItemResponseToReviewItemModelMapper
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
                    list = response.genres?.let(movieGenreMapper::map)
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
                    includeVideo = true,
                    page = requestModel.page
                )
                return MovieItemListModel(
                    list = response.results?.let(movieItemMapper::map)
                ).also {
                    it.status = SUCCESS_CODE
                    it.currentPage = response.page
                    it.totalPage = response.totalPages
                }
            }
        }.asFlow()
    }

    override fun getMovieDetails(movieId: Int): Flow<Resource<MovieDetailsModel>> {
        return object : NetworkResource<MovieDetailsModel>(
            dispatcherProvider = dispatcher
        ) {
            override suspend fun remoteFetch(): MovieDetailsModel {
                val response = homeService.getMovieDetails(
                    movieId = movieId,
                    language = QUERY_LANGUAGE_US_DEFAULT
                )
                return MovieDetailsModel(
                    details = movieItemMapper.map(response)
                ).also {
                    it.status = SUCCESS_CODE
                }
            }
        }.asFlow()
    }

    override fun getMovieReviewList(
        requestModel: ReviewItemRequestModel
    ): Flow<Resource<ReviewItemListModel>> {
        return object : NetworkResource<ReviewItemListModel>(
            dispatcherProvider = dispatcher
        ) {
            override suspend fun remoteFetch(): ReviewItemListModel {
                val response = homeService.getReviewPaginatedListByMovie(
                    movieId = requestModel.movieId,
                    language = QUERY_LANGUAGE_US_DEFAULT,
                    page = requestModel.page
                )
                return ReviewItemListModel(
                    list = response.results?.let(reviewItemMapper::map)
                ).also {
                    it.status = SUCCESS_CODE
                    it.currentPage = response.page
                    it.totalPage = response.totalPages
                    it.totalResult = response.totalResults
                }
            }
        }.asFlow()
    }

    override fun getMovieVideos(movieId: Int): Flow<Resource<MovieVideoListModel>> {
        return object : NetworkResource<MovieVideoListModel>(
            dispatcherProvider = dispatcher
        ) {
            override suspend fun remoteFetch(): MovieVideoListModel {
                val response = homeService.getMovieVideos(
                    movieId = movieId,
                    language = QUERY_LANGUAGE_US_DEFAULT
                )
                return MovieVideoListModel(
                    list = response.results?.let(movieVideoMapper::map)
                ).also {
                    it.status = SUCCESS_CODE
                }
            }
        }.asFlow()
    }
}