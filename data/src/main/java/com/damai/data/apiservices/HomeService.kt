package com.damai.data.apiservices

import com.damai.data.responses.MovieGenreListResponse
import com.damai.data.responses.MovieListResponse
import com.damai.data.responses.MovieReviewListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by damai007 on 18/August/2023
 */
interface HomeService {

    @GET("/3/genre/movie/list")
    suspend fun getMovieGenreList(
        @Query("language") language: String
    ): MovieGenreListResponse

    @GET("/3/discover/movie")
    suspend fun getMoviePaginatedListByGenre(
        @Query("with_genres") withGenres: String
    ): MovieListResponse

    @GET("/3/movie/{movie_id}/reviews")
    suspend fun getReviewPaginatedListByMovie(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieReviewListResponse
}