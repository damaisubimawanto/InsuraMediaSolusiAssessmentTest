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
    var popularity: Double? = null

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

    /* More fields in movie details. */
    @SerializedName("budget")
    var budget: Int? = null

    @SerializedName("genres")
    var genres: List<MovieGenreResponse>? = null

    @SerializedName("homepage")
    var homepage: String? = null

    @SerializedName("imdb_id")
    var imdbId: String? = null

    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompanyResponse>? = null

    @SerializedName("revenue")
    var revenue: Int? = null

    @SerializedName("runtime")
    var runtime: Int? = null

    @SerializedName("status")
    var status: String? = null

    @SerializedName("tagline")
    var tagline: String? = null
}

class ProductionCompanyResponse {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("logo_path")
    var logoPath: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("origin_country")
    var originCountry: String? = null
}