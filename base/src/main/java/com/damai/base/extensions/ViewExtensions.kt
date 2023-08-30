package com.damai.base.extensions

import android.content.res.ColorStateList
import android.view.View
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.damai.base.utils.Constants.GLIDE_CROSS_FADE

/**
 * Created by damai007 on 18/August/2023
 */

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.setCustomOnClickListener(listener: View.OnClickListener) {
    setOnClickListener(object : View.OnClickListener {
        var lastTimeClicked = 0L

        override fun onClick(p0: View?) {
            if (System.currentTimeMillis() - lastTimeClicked > 1_500L) {
                lastTimeClicked = System.currentTimeMillis()
                listener.onClick(p0)
            }
        }
    })
}

fun AppCompatImageView.loadImageWithCenterCrop(
    url: String?
) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade(GLIDE_CROSS_FADE))
        .into(this)
}

fun AppCompatImageView.setCustomTint(
    @ColorRes colorRes: Int
) {
    ImageViewCompat.setImageTintList(
        this,
        ColorStateList.valueOf(ContextCompat.getColor(context, colorRes))
    )
}

fun AppCompatTextView.setCustomTextColor(
    @ColorRes colorRes: Int
) {
    setTextColor(ContextCompat.getColor(context, colorRes))
}