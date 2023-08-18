package com.damai.data.responses

import com.damai.base.BaseResponse
import com.google.gson.annotations.SerializedName

/**
 * Created by damai007 on 18/August/2023
 */
class MovieListResponse : BaseResponse() {
    @SerializedName("results")
    var results: List<MovieItemResponse>? = null
}

class MovieItemResponse {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("adult")
    var adult: Boolean? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("original_language")
    var originalLanguage: String? = null

    @SerializedName("original_title")
    var originalTitle: String? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("popularity")
    var popularity: Int? = null

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("release_date")
    var releaseDate: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("video")
    var video: Boolean? = null

    @SerializedName("vote_average")
    var voteAverage: Double? = null

    @SerializedName("vote_count")
    var voteCount: Int? = null
}