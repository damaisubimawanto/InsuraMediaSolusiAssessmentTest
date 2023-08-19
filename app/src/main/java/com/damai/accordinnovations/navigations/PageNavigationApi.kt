package com.damai.accordinnovations.navigations

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

/**
 * Created by damai007 on 18/August/2023
 */
interface PageNavigationApi {

    fun navigateToMovieDetailActivity(
        context: Context,
        launcher: ActivityResultLauncher<Intent>,
        movieId: Int
    )
}