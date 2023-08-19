package com.damai.domain.repositories

import com.damai.base.networks.Resource
import com.damai.domain.models.MovieDetailsModel
import com.damai.domain.models.MovieGenreListModel
import com.damai.domain.models.MovieItemByGenreRequestModel
import com.damai.domain.models.MovieItemListModel
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

    fun getMovieDetails(
        movieId: Int
    ): Flow<Resource<MovieDetailsModel>>
}