package com.damai.data.responses

import com.damai.base.BaseResponse
import com.google.gson.annotations.SerializedName

/**
 * Created by damai007 on 30/August/2023
 */
class MovieVideoListResponse: BaseResponse() {
    @SerializedName("results")
    var results: List<MovieVideoResponse>? = null
}

class MovieVideoResponse {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("iso_639_1")
    var iso639of1: String? = null

    @SerializedName("iso_3166_1")
    var iso3166of1: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("key")
    var key: String? = null

    @SerializedName("site")
    var site: String? = null

    @SerializedName("size")
    var size: Int? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("official")
    var official: Boolean? = null

    @SerializedName("published_at")
    var publishedAt: String? = null
}