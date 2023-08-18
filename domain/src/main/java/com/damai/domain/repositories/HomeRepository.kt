package com.damai.domain.repositories

import com.damai.base.networks.Resource
import com.damai.domain.models.MovieGenreListModel
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

/**
 * Created by damai007 on 18/August/2023
 */
interface HomeRepository {

    @Throws(Exception::class)
    fun getMovieGenreList(): Flow<Resource<MovieGenreListModel>>
}