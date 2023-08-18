package com.damai.base

import com.google.gson.annotations.SerializedName

/**
 * Created by damai007 on 18/August/2023
 */
open class BaseResponse {
    @SerializedName("page")
    var page: Int? = null

    @SerializedName("total_pages")
    var totalPages: Int? = null

    @SerializedName("total_results")
    var totalResults: Int? = null
}