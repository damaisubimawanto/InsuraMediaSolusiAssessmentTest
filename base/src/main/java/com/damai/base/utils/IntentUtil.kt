package com.damai.base.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import com.damai.base.R
import com.damai.base.extensions.showShortToast
import com.damai.base.utils.Constants.BASE_VIMEO_URL
import com.damai.base.utils.Constants.BASE_YOUTUBE_URL

/**
 * Created by damai007 on 30/August/2023
 */
class IntentUtil {

    companion object {
        fun openYoutubeApp(
            context: Context,
            youtubeId: String
        ) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("$BASE_YOUTUBE_URL$youtubeId")
            intent.setPackage("com.google.android.youtube")
            val info = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.queryIntentActivities(
                    intent,
                    PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_DEFAULT_ONLY.toLong())
                )
            } else {
                context.packageManager.queryIntentActivities(
                    intent,
                    PackageManager.MATCH_DEFAULT_ONLY
                )
            }
            if (info.size > 0) {
                context.startActivity(intent)
            } else {
                context.showShortToast(
                    message = context.getString(
                        R.string.no_selected_app_installed,
                        VideoPlatformType.Youtube.type
                    )
                )
            }
        }

        fun openVimeoApp(
            context: Context,
            vimeoId: String
        ) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("$BASE_VIMEO_URL$vimeoId")
            intent.setPackage("com.vimeo.android.videoapp")
            val info = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.queryIntentActivities(
                    intent,
                    PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_DEFAULT_ONLY.toLong())
                )
            } else {
                context.packageManager.queryIntentActivities(
                    intent,
                    PackageManager.MATCH_DEFAULT_ONLY
                )
            }
            if (info.size > 0) {
                context.startActivity(intent)
            } else {
                context.showShortToast(
                    message = context.getString(
                        R.string.no_selected_app_installed,
                        VideoPlatformType.Vimeo.type
                    )
                )
            }
        }
    }
}