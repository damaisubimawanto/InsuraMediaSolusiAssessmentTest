package com.damai.base.utils

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearSmoothScroller

/**
 * Created by damai007 on 30/August/2023
 *
 * Special thanks to furkanbzkurt.
 * Reference link: https://stackoverflow.com/a/67136484/4569155
 */
class CenterSmoothScroller(context: Context) : LinearSmoothScroller(context) {

    companion object {
        private const val MILLISECONDS_PER_INCH = 150f
    }

    override fun calculateDtToFit(
        viewStart: Int,
        viewEnd: Int,
        boxStart: Int,
        boxEnd: Int,
        snapPreference: Int
    ): Int {
        return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2)
    }

    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
        displayMetrics?.let {
            return MILLISECONDS_PER_INCH / it.densityDpi
        }
        return super.calculateSpeedPerPixel(displayMetrics)
    }
}