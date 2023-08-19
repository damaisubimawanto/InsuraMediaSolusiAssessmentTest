package com.damai.base.bindingadapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.damai.base.extensions.loadImageWithCenterCrop
import com.damai.base.utils.Constants.BASE_IMAGE_URL
import com.damai.base.utils.SimpleDateUtil

/**
 * Created by damai007 on 19/August/2023
 */
object ViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun bindLoadImage(view: AppCompatImageView, url: String?) {
        view.loadImageWithCenterCrop(url = "$BASE_IMAGE_URL$url")
    }

    @JvmStatic
    @BindingAdapter(value = ["movieTitle", "movieReleaseDate"], requireAll = true)
    fun bindMovieTitleWithYear(view: AppCompatTextView, movieTitle: String, movieReleaseDate: String) {
        val movieDate = SimpleDateUtil.parseStringToDate(
            givenDateString = movieReleaseDate,
            sourceFormat = SimpleDateUtil.DateFormat.YYYY_MM_DD
        )
        val movieYear = movieDate?.let {
            SimpleDateUtil.parseDateToString(
                givenDate = it,
                outputFormat = SimpleDateUtil.DateFormat.YYYY
            )
        }.orEmpty()
        val titleText = "$movieTitle ($movieYear)"
        view.text = titleText
    }

    @JvmStatic
    @BindingAdapter("movieYear")
    fun bindMovieYear(view: AppCompatTextView, movieReleaseDate: String?) {
        val movieDate = SimpleDateUtil.parseStringToDate(
            givenDateString = movieReleaseDate.orEmpty(),
            sourceFormat = SimpleDateUtil.DateFormat.YYYY_MM_DD
        )
        val movieYear = movieDate?.let {
            SimpleDateUtil.parseDateToString(
                givenDate = it,
                outputFormat = SimpleDateUtil.DateFormat.YYYY
            )
        }.orEmpty()
        val titleText = "($movieYear)"
        view.text = titleText
    }

    @JvmStatic
    @BindingAdapter("movieRuntime")
    fun bindMovieRuntime(view: AppCompatTextView, movieRuntime: Int) {
        val runtimeText = "$movieRuntime min"
        view.text = runtimeText
    }

    @JvmStatic
    @BindingAdapter("movieRating")
    fun bindMovieRating(view: AppCompatTextView, movieRating: Double) {
        view.text = "$movieRating"
    }
}