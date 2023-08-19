package com.damai.accordinnovations.navigations

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.damai.accordinnovations.ui.detail.MovieDetailActivity
import com.damai.base.utils.Constants.ARGS_MOVIE_ID

/**
 * Created by damai007 on 18/August/2023
 */
class PageNavigationApiImpl : PageNavigationApi {

    override fun navigateToMovieDetailActivity(
        context: Context,
        launcher: ActivityResultLauncher<Intent>,
        movieId: Int
    ) {
        Intent(context, MovieDetailActivity::class.java).apply {
            putExtra(ARGS_MOVIE_ID, movieId)
        }.also {
            launcher.launch(it)
        }
    }
}