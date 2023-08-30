package com.damai.base.utils

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by damai007 on 30/August/2023
 *
 * Special thanks to furkanbzkurt.
 * Reference link: https://stackoverflow.com/a/67136484/4569155
 */
class CenterLayoutManager : LinearLayoutManager {

    constructor(context: Context) : super(context)
    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView?,
        state: RecyclerView.State?,
        position: Int
    ) {
        recyclerView?.let {
            val centerSmoothScroller = CenterSmoothScroller(it.context)
            centerSmoothScroller.targetPosition = if (position < 0) {
                0
            } else {
                position
            }
            startSmoothScroll(centerSmoothScroller)
        }
    }
}