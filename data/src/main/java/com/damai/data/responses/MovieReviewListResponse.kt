package com.damai.data.responses

import com.damai.base.BaseResponse
import com.google.gson.annotations.SerializedName

/**
 * Created by damai007 on 18/August/2023
 */
class MovieReviewListResponse : BaseResponse() {
    @SerializedName("results")
    var results: List<ReviewItemResponse>? = null
}

class ReviewItemResponse {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("author")
    var author: String? = null

    @SerializedName("author_details")
    var authorDetails: AuthorDetailResponse? = null

    @SerializedName("content")
    var content: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("updated_at")
    var updatedAt: String? = null
}

class AuthorDetailResponse {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("username")
    var username: String? = null

    @SerializedName("avatar_path")
    var avatarPath: String? = null

    @SerializedName("rating")
    var rating: Int? = null
}