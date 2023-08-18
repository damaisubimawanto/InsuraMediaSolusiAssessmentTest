package com.damai.data.responses

import com.damai.base.BaseResponse
import com.google.gson.annotations.SerializedName

/**
 * Created by damai007 on 18/August/2023
 */
class MovieGenreListResponse : BaseResponse() {
    @SerializedName("genres")
    var genres: List<MovieGenreResponse>? = null
}

class MovieGenreResponse {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("name")
    var name: String? = null
}