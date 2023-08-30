package com.damai.domain.repositories

import com.damai.base.networks.Resource
import com.damai.domain.models.MovieDetailsModel
import com.damai.domain.models.MovieGenreListModel
import com.damai.domain.models.MovieItemByGenreRequestModel
import com.damai.domain.models.MovieItemListModel
import com.damai.domain.models.MovieVideoListModel
import com.damai.domain.models.ReviewItemListModel
import com.damai.domain.models.ReviewItemRequestModel
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

/**
 * Created by damai007 on 18/August/2023
 */
interface HomeRepository {

    @Throws(Exception::class)
    fun getMovieGenreList(): Flow<Resource<MovieGenreListModel>>

    @Throws(Exception::class)
    fun getMovieItemListByGenre(
        requestModel: MovieItemByGenreRequestModel
    ): Flow<Resource<MovieItemListModel>>

    @Throws(Exception::class)
    fun getMovieDetails(
        movieId: Int
    ): Flow<Resource<MovieDetailsModel>>

    @Throws(Exception::class)
    fun getMovieReviewList(
        requestModel: ReviewItemRequestModel
    ): Flow<Resource<ReviewItemListModel>>

    @Throws(Exception::class)
    fun getMovieVideos(
        movieId: Int
    ): Flow<Resource<MovieVideoListModel>>
}