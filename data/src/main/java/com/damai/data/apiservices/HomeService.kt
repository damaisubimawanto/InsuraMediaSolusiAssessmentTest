package com.damai.data.apiservices

import com.damai.data.responses.MovieGenreListResponse
import com.damai.data.responses.MovieItemResponse
import com.damai.data.responses.MovieListResponse
import com.damai.data.responses.MovieReviewListResponse
import com.damai.data.responses.MovieVideoListResponse
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
        @Query("with_genres") withGenres: String,
        @Query("sort_by") sortBy: String,
        @Query("language") language: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean,
        @Query("page") page: Int
    ): MovieListResponse

    @GET("/3/movie/{movie_id}/reviews")
    suspend fun getReviewPaginatedListByMovie(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieReviewListResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): MovieItemResponse

    @GET("/3/movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): MovieVideoListResponse
}